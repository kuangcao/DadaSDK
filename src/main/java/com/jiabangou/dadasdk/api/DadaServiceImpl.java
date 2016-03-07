package com.jiabangou.dadasdk.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiabangou.dadasdk.exception.DadaErrorException;
import com.jiabangou.dadasdk.model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.*;

public class DadaServiceImpl implements DadaService {

    private final static String BASE_URL = "http://public.imdada.cn";
    private final static String TEST_BASE_URL = "http://public.ga.qa.imdada.cn";
    public static final String SIGNATURE_CONST = "dada";
    /**
     * 全局的是否正在刷新access token的锁
     */
    protected final Object globalAccessTokenRefreshLock = new Object();
    /**
     * 全局的是否正在刷新grant code的锁
     */
    protected final Object globalGrantCodeRefreshLock = new Object();

    protected DadaConfigStorage dadaConfigStorage;

    protected CloseableHttpClient httpClient;

    protected HttpHost httpProxy;

    protected DadaCallbackHandler dadaCallbackHandler;

    private int retrySleepMillis = 1000;

    private int maxRetryTimes = 5;

    private boolean isTest;

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }


    @Override
    public String getAccessToken() throws DadaErrorException {
        return getAccessToken(false);
    }

    public String getBaseUrl() {
        return isTest ? TEST_BASE_URL : BASE_URL;
    }

    protected CloseableHttpClient getHttpclient() {
        return httpClient;
    }

    public void setDadaCallbackHandler(DadaCallbackHandler dadaCallbackHandler) {
        this.dadaCallbackHandler = dadaCallbackHandler;
    }

    public void setDadaConfigStorage(DadaConfigStorage dadaConfigProvider) {
        this.dadaConfigStorage = dadaConfigProvider;

        String http_proxy_host = dadaConfigStorage.getHttp_proxy_host();
        int http_proxy_port = dadaConfigStorage.getHttp_proxy_port();
        String http_proxy_username = dadaConfigStorage.getHttp_proxy_username();
        String http_proxy_password = dadaConfigStorage.getHttp_proxy_password();

        final HttpClientBuilder builder = HttpClients.custom();
        if (StringUtils.isNotBlank(http_proxy_host)) {
            // 使用代理服务器
            if (StringUtils.isNotBlank(http_proxy_username)) {
                // 需要用户认证的代理服务器
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(http_proxy_host, http_proxy_port),
                        new UsernamePasswordCredentials(http_proxy_username, http_proxy_password));
                builder
                        .setDefaultCredentialsProvider(credsProvider);
            }
            httpProxy = new HttpHost(http_proxy_host, http_proxy_port);
        }
        httpClient = builder.build();
    }

    @Override
    public String getAccessToken(boolean forceRefresh) throws DadaErrorException {
        if (forceRefresh) {
            dadaConfigStorage.expireAccessToken();
        }
        // access token 不存在
        if (!dadaConfigStorage.hasAccessToken()) {
            synchronized (globalGrantCodeRefreshLock) {
                if (!dadaConfigStorage.hasAccessToken()) {
                    DadaGrantCode grantCode = getGrantCode();
                    getAccessToken(grantCode);
                }
            }
        }
        // access token 过期
        if (dadaConfigStorage.isAccessTokenExpired()) {
            synchronized (globalAccessTokenRefreshLock) {
                if (dadaConfigStorage.isAccessTokenExpired()) {
                    try {
                        refreshAccessToken();
                    } catch (DadaErrorException e) {
                        if (e.getCode() == 2216 || e.getCode() == 2215) {
                            // refresh token 已过期
                            DadaGrantCode grantCode = getGrantCode();
                            getAccessToken(grantCode);
                        } else {
                            throw e;
                        }
                    }
                }
            }
        }
        return dadaConfigStorage.getAccessToken();
    }

    private String doGet(String url) throws DadaErrorException {
        try {
            HttpGet httpGet = new HttpGet(url);
            if (httpProxy != null) {
                RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
                httpGet.setConfig(config);
            }
            CloseableHttpResponse response = getHttpclient().execute(httpGet);
            String resultContent = new BasicResponseHandler().handleResponse(response);
            DadaError error = DadaError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
                throw new DadaErrorException(error);
            }
            return resultContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String doPost(String url, List<NameValuePair> nvps) throws DadaErrorException {
        try {
            HttpPost httpPost = new HttpPost(url);
            if (httpProxy != null) {
                RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
                httpPost.setConfig(config);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = getHttpclient().execute(httpPost);
            String resultContent = new BasicResponseHandler().handleResponse(response);
            DadaError error = DadaError.fromJson(resultContent);
            if (error.getErrorCode() != 0) {
                throw new DadaErrorException(error);
            }
            return resultContent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DadaAccessToken refreshAccessToken() throws DadaErrorException {
        String url = String.format("%s/oauth/refresh_token/?grant_type=refresh_token&app_key=%s&refresh_token=%s",
                getBaseUrl(), dadaConfigStorage.getAppKey(), dadaConfigStorage.getRefreshToken());
        String resultContent = doGet(url);
        DadaAccessToken accessToken = DadaAccessToken.fromJson(resultContent);
        dadaConfigStorage.updateAccessToken(accessToken);
        return accessToken;
    }

    private DadaAccessToken getAccessToken(DadaGrantCode grantCode) throws DadaErrorException {
        String url = String.format("%s/oauth/access_token/?grant_type=authorization_code&app_key=%s&grant_code=%s",
                getBaseUrl(), dadaConfigStorage.getAppKey(), grantCode.getGrant_code());
        String resultContent = doGet(url);
        DadaAccessToken accessToken = DadaAccessToken.fromJson(resultContent);
        dadaConfigStorage.updateAccessToken(accessToken);
        return accessToken;
    }

    private DadaGrantCode getGrantCode() throws DadaErrorException {
        String url = String.format("%s/oauth/authorize/?app_key=%s&scope=dada_base", getBaseUrl(), dadaConfigStorage.getAppKey());
        String resultContent = doGet(url);
        return DadaGrantCode.fromJson(resultContent);
    }

    public DadaAddOrderResp addOrder(DadaAddOrderReq req) throws DadaErrorException {
        String url = String.format("%s/v1_0/addOrder/", getBaseUrl());
        List<NameValuePair> params = new ArrayList<>();
        addSignature(params);
        params.addAll(toNameValuePairs(req));

        String resultContent = doPost(url, params);
        return DadaAddOrderResp.fromJson(resultContent);
    }

    public DadaCancelOrderResp cancelOrder(DadaCancelOrderReq req) throws DadaErrorException {
        String url = null;
        if (isTest) {
            url = String.format("%s/v1_0/cancelOrder/", getBaseUrl());
        } else {
            url = String.format("%s/v2_0/cancelOrder/", getBaseUrl());
        }
        List<NameValuePair> params= toNameValuePairs(req);
        addSignature(params);
        String resultContent = doPost(url, params);
        return DadaCancelOrderResp.fromJson(resultContent);
    }

    public DadaOrderInfoResp getOrderInfo(String orderId) throws DadaErrorException {
        String url = String.format("%s/v1_0/getOrderInfo/", getBaseUrl());
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        addSignature(nvps);
        nvps.add(new BasicNameValuePair("order_id", orderId));
        String resultContent = doPost(url, nvps);
        return DadaOrderInfoResp.fromJson(resultContent);
    }

    public DadaDmInfo getDmInfo(int dmId, String orderId) throws DadaErrorException {
        String url = String.format("%s/v1_0/getDmInfo/", getBaseUrl());
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        addSignature(nvps);
        nvps.add(new BasicNameValuePair("dm_id", String.valueOf(dmId)));
        nvps.add(new BasicNameValuePair("order_id", String.valueOf(orderId)));

        String resultContent = doPost(url, nvps);
        return DadaDmInfo.fromJson(resultContent);
    }

    public List<DadaCancelReason> getCancelReasons() throws DadaErrorException {
        String url = String.format("%s/v1_0/getCancelReasons/", getBaseUrl());

        String resultContent = doGet(url);
        return DadaCancelReason.fromJson(resultContent);
    }

    public long appointNewOrder(DadaAppointNewOrderReq appointNewOrderReq) throws DadaErrorException {
        String url = String.format("%s/v1_0/appointNewOrder/", getBaseUrl());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        addSignature(params);
        params.addAll(toNameValuePairs(appointNewOrderReq));
        String resultContent = doPost(url, params);
        JSONObject jsonObject = JSON.parseObject(resultContent);
        return jsonObject.getLongValue("orderid");
    }

    public void acceptOrder(String orderId) throws DadaErrorException {
        if (isTest) {
            String url = String.format("%s/v1_0/acceptOrder/", getBaseUrl());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            addSignature(params);
            params.add(new BasicNameValuePair("order_id", orderId));
            doPost(url, params);
        }
    }

    public void rejectOrder(String orderId) throws DadaErrorException {
        if (isTest) {
            String url = String.format("%s/v1_0/rejectOrder/", getBaseUrl());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            addSignature(params);
            params.add(new BasicNameValuePair("order_id", orderId));
            doPost(url, params);
        }
    }

    public void fetchOrder(String orderId) throws DadaErrorException {
        if (isTest) {
            String url = String.format("%s/v1_0/fetchOrder/", getBaseUrl());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            addSignature(params);
            params.add(new BasicNameValuePair("order_id", orderId));
            doPost(url, params);
        }
    }

    public void finishOrder(String orderId) throws DadaErrorException {
        if (isTest) {
            String url = String.format("%s/v1_0/finishOrder/", getBaseUrl());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            addSignature(params);
            params.add(new BasicNameValuePair("order_id", orderId));
            doPost(url, params);
        }
    }

    public void updateTips(DadaUpdateTipsReq req) throws DadaErrorException {
        String url = String.format("%s/v1_0/tips/update/", getBaseUrl());
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        addSignature(params);
        params.addAll(toNameValuePairs(req));
        doPost(url, params);
    }

    public List<DadaCity> getCities() throws DadaErrorException {
        String url = String.format("%s/v1_0/getCity/?token=%s", getBaseUrl(), getAccessToken());
        String content = doGet(url);
        return DadaCity.fromJson(content);
    }

    private void addSignature(List<NameValuePair> nvps) throws DadaErrorException {
        DadaApiSignature signature = createApiSignature();
        nvps.add(new BasicNameValuePair("token", signature.getToken()));
        nvps.add(new BasicNameValuePair("timestamp", String.valueOf(signature.getTimestamp())));
        nvps.add(new BasicNameValuePair("signature", signature.getSignature()));
    }

    public void orderCallback(DadaOrderCallbackMessage callbackMessage, Map<String, Object> params) {
        if (this.dadaCallbackHandler != null) {
            this.dadaCallbackHandler.handler(callbackMessage, params);
        }
    }

    public DadaApiSignature createApiSignature() throws DadaErrorException {
        long timestamp = System.currentTimeMillis() / 1000;
        String accssToken = getAccessToken();

        final List<String> list = new ArrayList<String>(3);
        list.add(accssToken);
        list.add(String.valueOf(timestamp));
        list.add(SIGNATURE_CONST);
        Collections.sort(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            stringBuilder.append(str);
        }
        DadaApiSignature signature = new DadaApiSignature();
        signature.setSignature(DigestUtils.md5Hex(stringBuilder.toString()));
        signature.setTimestamp(timestamp);
        signature.setToken(accssToken);
        return signature;
    }


    public List<NameValuePair> toNameValuePairs(Object object) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        JSONObject jsonObject = (JSONObject) JSON.toJSON(object);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        return nvps;
    }

}

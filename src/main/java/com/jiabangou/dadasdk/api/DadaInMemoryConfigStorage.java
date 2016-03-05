package com.jiabangou.dadasdk.api;

import com.jiabangou.dadasdk.model.DadaAccessToken;

public class DadaInMemoryConfigStorage implements DadaConfigStorage {

    protected volatile String accessToken;
    protected volatile String refreshToken;
    protected volatile long expiresTime;
    protected volatile String scope;

    protected volatile String appKey;

    protected volatile String http_proxy_host;
    protected volatile int http_proxy_port;
    protected volatile String http_proxy_username;
    protected volatile String http_proxy_password;

    @Override
    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getRefreshToken() {
        return refreshToken;
    }

    @Override
    public boolean hasAccessToken() {
        return accessToken != null;
    }

    @Override
    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    @Override
    public void expireAccessToken() {
        this.expiresTime = 0;
    }

    @Override
    public void updateAccessToken(DadaAccessToken accessToken) {
        this.accessToken = accessToken.getAccess_token();
        this.refreshToken = accessToken.getRefresh_token();
        this.expiresTime = System.currentTimeMillis() + (accessToken.getExpires_in() - 200) * 1000l;
        this.scope = accessToken.getScope();
    }

    @Override
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getHttp_proxy_host() {
        return http_proxy_host;
    }

    public void setHttp_proxy_host(String http_proxy_host) {
        this.http_proxy_host = http_proxy_host;
    }

    public int getHttp_proxy_port() {
        return http_proxy_port;
    }

    public void setHttp_proxy_port(int http_proxy_port) {
        this.http_proxy_port = http_proxy_port;
    }

    public String getHttp_proxy_username() {
        return http_proxy_username;
    }

    public void setHttp_proxy_username(String http_proxy_username) {
        this.http_proxy_username = http_proxy_username;
    }

    public String getHttp_proxy_password() {
        return http_proxy_password;
    }

    public void setHttp_proxy_password(String http_proxy_password) {
        this.http_proxy_password = http_proxy_password;
    }

}

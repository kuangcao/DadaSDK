package com.jiabangou.dadasdk.api;

import com.jiabangou.dadasdk.model.DadaAccessToken;

public interface DadaConfigStorage {

    String getAccessToken();

    String getRefreshToken();

    boolean hasAccessToken();

    boolean isAccessTokenExpired();

    /**
     * 强制将access token过期掉
     */
    void expireAccessToken();

    /**
     * 应该是线程安全的
     * @param accessToken
     */
    void updateAccessToken(DadaAccessToken accessToken);

    String getAppKey();

    String getHttp_proxy_host();

    int getHttp_proxy_port();

    String getHttp_proxy_username();

    String getHttp_proxy_password();


}

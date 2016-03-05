package com.jiabangou.dadasdk.model;

public class DadaGrantCode extends DadaResp {
    private long grant_code;

    public long getGrant_code() {
        return grant_code;
    }

    public void setGrant_code(long grant_code) {
        this.grant_code = grant_code;
    }

    public static DadaGrantCode fromJson(String json) {
        return fromJson(json, DadaGrantCode.class, true);
    }

}

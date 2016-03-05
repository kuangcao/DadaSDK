package com.jiabangou.dadasdk.model;

public class DadaOrderInfoResp extends DadaResp {

    private int status_code;
    private String status;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static DadaOrderInfoResp fromJson(String json) {
        return fromJson(json, DadaOrderInfoResp.class, true);
    }

    @Override
    public String toString() {
        return "DadaOrderInfoResp{" +
                "status_code=" + status_code +
                ", status='" + status + '\'' +
                '}';
    }
}

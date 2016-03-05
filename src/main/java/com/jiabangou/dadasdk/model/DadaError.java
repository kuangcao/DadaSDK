package com.jiabangou.dadasdk.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiabangou.dadasdk.exception.ErrorCode;

public class DadaError extends DadaResp {

    private int errorCode;

    private String errorMsg;

    private String json;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static DadaError fromJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        if ("fail".equals(jsonObject.getString("status"))) {
            DadaError error = new DadaError();
            error.setErrorCode(jsonObject.getIntValue("errorCode"));
            error.setErrorMsg(ErrorCode.getMsg(error.getErrorCode()));
            error.setJson(json);
            return error;
        }
        return new DadaError();
    }

    @Override
    public String toString() {
        return "达达错误: errcode=" + errorCode + ", errmsg=" + errorMsg + "\njson:" + json;
    }

}

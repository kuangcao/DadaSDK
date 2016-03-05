package com.jiabangou.dadasdk.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class DadaResp implements Serializable {

    protected static <T> T fromJson(String json, Class<T> t, boolean hasResult) {
        if (hasResult) {
            JSONObject jsonObject = JSON.parseObject(json);
            return jsonObject.getObject("result", t);
        } else {
            return JSON.parseObject(json, t);
        }
    }

}

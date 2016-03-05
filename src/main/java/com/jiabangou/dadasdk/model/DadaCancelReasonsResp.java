package com.jiabangou.dadasdk.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DadaCancelReasonsResp extends DadaResp {
    private long id;
    private String info;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "DadaCancelReasonsResp{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }

    public static List<DadaCancelReasonsResp> fromJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<DadaCancelReasonsResp> list = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject1 = (JSONObject)object;
            DadaCancelReasonsResp resp = new DadaCancelReasonsResp();
            resp.setId(jsonObject1.getLongValue("id"));
            resp.setInfo(jsonObject1.getString("info"));
            list.add(resp);
        }
        return list;
    }

}

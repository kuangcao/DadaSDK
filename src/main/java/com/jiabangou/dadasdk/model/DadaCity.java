package com.jiabangou.dadasdk.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DadaCity extends DadaResp {
    private String name;
    private String citycode;
    private String adcode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public static List<DadaCity> fromJson(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<DadaCity> dadaCities = new ArrayList<DadaCity>();
        for (Object object : jsonArray) {
            DadaCity dadaCity = new DadaCity();
            dadaCity.setName(((JSONObject)object).getString("name"));
            dadaCity.setCitycode(((JSONObject)object).getString("citycode"));
            dadaCity.setAdcode(((JSONObject)object).getString("adcode"));
            dadaCities.add(dadaCity);
        }
        return dadaCities;
    }

    @Override
    public String toString() {
        return "DadaCity{" +
                "name='" + name + '\'' +
                ", citycode='" + citycode + '\'' +
                ", adcode='" + adcode + '\'' +
                '}';
    }
}

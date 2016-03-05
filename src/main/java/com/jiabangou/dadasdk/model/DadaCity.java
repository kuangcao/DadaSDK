package com.jiabangou.dadasdk.model;

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

    @Override
    public String toString() {
        return "DadaCity{" +
                "name='" + name + '\'' +
                ", citycode='" + citycode + '\'' +
                ", adcode='" + adcode + '\'' +
                '}';
    }
}

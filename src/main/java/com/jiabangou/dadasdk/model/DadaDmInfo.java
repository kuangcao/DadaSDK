package com.jiabangou.dadasdk.model;

public class DadaDmInfo extends DadaResp {
    //配送员姓名
    private String dm_name;
    //配送员手机号
    private String dm_mobile;
    //配送员纬度
    private double dm_lat;
    //配送员经度
    private double dm_lng;
    //更新时间
    private long update_time;

    public String getDm_name() {
        return dm_name;
    }

    public void setDm_name(String dm_name) {
        this.dm_name = dm_name;
    }

    public String getDm_mobile() {
        return dm_mobile;
    }

    public void setDm_mobile(String dm_mobile) {
        this.dm_mobile = dm_mobile;
    }

    public double getDm_lat() {
        return dm_lat;
    }

    public void setDm_lat(double dm_lat) {
        this.dm_lat = dm_lat;
    }

    public double getDm_lng() {
        return dm_lng;
    }

    public void setDm_lng(double dm_lng) {
        this.dm_lng = dm_lng;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public static DadaDmInfo fromJson(String json) {
        return fromJson(json, DadaDmInfo.class, true);
    }

    @Override
    public String toString() {
        return "DadaDmInfo{" +
                "dm_name='" + dm_name + '\'' +
                ", dm_mobile='" + dm_mobile + '\'' +
                ", dm_lat=" + dm_lat +
                ", dm_lng=" + dm_lng +
                ", update_time=" + update_time +
                '}';
    }

}

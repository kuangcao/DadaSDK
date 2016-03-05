package com.jiabangou.dadasdk.model;

import java.io.Serializable;

public class DadaUpdateTipsReq implements Serializable {

    private String order_id; //	int	否	达达系统订单id, 可不传
    private String origin_id; //	int	是	添加订单接口中的origin_id值
    private int tips; //	int	是	小费（以元为单位）
    private String city_code;  //	string	是	城市区号

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public int getTips() {
        return tips;
    }

    public void setTips(int tips) {
        this.tips = tips;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    @Override
    public String toString() {
        return "DadaUpdateTipsReq{" +
                "order_id='" + order_id + '\'' +
                ", origin_id='" + origin_id + '\'' +
                ", tips=" + tips +
                ", city_code='" + city_code + '\'' +
                '}';
    }
}

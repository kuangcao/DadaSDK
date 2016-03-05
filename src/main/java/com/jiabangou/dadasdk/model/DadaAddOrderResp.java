/*
 *  Copyright (c) 2015.  meicanyun.com Corporation Limited.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  Yage Company. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with meicanyun.com.
 */

package com.jiabangou.dadasdk.model;

/**
 * 达达创建订单接口返回数据
 */
public class DadaAddOrderResp extends DadaResp {

    private float fee;
    private float distance;
    //达达返回的订单ID
    private String orderid;
    //收货码
    private String finishCode;

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getFinishCode() {
        return finishCode;
    }

    public void setFinishCode(String finishCode) {
        this.finishCode = finishCode;
    }

    public static DadaAddOrderResp fromJson(String json) {
        return fromJson(json, DadaAddOrderResp.class, false);
    }

    @Override
    public String toString() {
        return "DadaAddOrderResp{" +
                "fee=" + fee +
                ", distance=" + distance +
                ", orderid='" + orderid + '\'' +
                ", finishCode='" + finishCode + '\'' +
                '}';
    }
}

package com.jiabangou.dadasdk.model;

import java.io.Serializable;

public class DadaCancelOrderReq implements Serializable {

    //添加订单接口中的origin_id值
    private String order_id;
    //取消原因ID 详情见获取取消理由
    private String cancel_reason_id;
    //取消原因（取消原因ID对应其他，则必须传原因）
    private String cancel_reason;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCancel_reason_id() {
        return cancel_reason_id;
    }

    public void setCancel_reason_id(String cancel_reason_id) {
        this.cancel_reason_id = cancel_reason_id;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    @Override
    public String toString() {
        return "DadaCancelOrderReq{" +
                "order_id='" + order_id + '\'' +
                ", cancel_reason_id='" + cancel_reason_id + '\'' +
                ", cancel_reason='" + cancel_reason + '\'' +
                '}';
    }

}

package com.jiabangou.dadasdk.model;

public class DadaCancelOrderResp extends DadaResp {

    //配送员接单时间，unix时间戳
    private long accept_time;
    //取消订单时间，unix时间戳
    private long cancel_time;
    //扣除违约金金额，单位元
    private double deduct_fee;

    public long getAccept_time() {
        return accept_time;
    }

    public void setAccept_time(long accept_time) {
        this.accept_time = accept_time;
    }

    public long getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(long cancel_time) {
        this.cancel_time = cancel_time;
    }

    public double getDeduct_fee() {
        return deduct_fee;
    }

    public void setDeduct_fee(double deduct_fee) {
        this.deduct_fee = deduct_fee;
    }

    @Override
    public String toString() {
        return "DadaCancelOrderResp{" +
                "accept_time=" + accept_time +
                ", cancel_time=" + cancel_time +
                ", deduct_fee=" + deduct_fee +
                '}';
    }

    public static DadaCancelOrderResp fromJson(String json) {
        return fromJson(json, DadaCancelOrderResp.class, false);
    }

}

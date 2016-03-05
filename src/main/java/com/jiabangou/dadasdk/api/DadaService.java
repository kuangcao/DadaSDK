package com.jiabangou.dadasdk.api;

import com.jiabangou.dadasdk.exception.DadaErrorException;
import com.jiabangou.dadasdk.model.*;

import java.util.List;

public interface DadaService {

    /**
     * 获取access_token, 不强制刷新access_token
     * @see #getAccessToken(boolean)
     * @return
     * @throws DadaErrorException
     */
    String getAccessToken() throws DadaErrorException;

    /**
     * <pre>
     * 获取access_token，本方法线程安全
     * 且在多线程同时刷新时只刷新一次，避免超出2000次/日的调用次数上限
     *
     * 另：本service的所有方法都会在access_token过期是调用此方法
     *
     * 程序员在非必要情况下尽量不要主动调用此方法
     * </pre>
     * @param forceRefresh 强制刷新
     * @return
     * @throws DadaErrorException
     */
    String getAccessToken(boolean forceRefresh) throws DadaErrorException;

    /**
     * <pre>
     * 在达达平台发布订单
     * 详情请见: https://open.imdada.cn/wiki/addOrder/
     * </pre>
     * @param req
     * @return
     * @throws DadaErrorException
     */
    DadaAddOrderResp addOrder(DadaAddOrderReq req) throws DadaErrorException;

    /**
     * <pre>
     * 在达达平台删除订单
     * 详情请见: https://open.imdada.cn/wiki/addOrder/
     * </pre>
     * @param req
     * @return
     * @throws DadaErrorException
     */
    DadaCancelOrderResp cancelOrder(DadaCancelOrderReq req) throws DadaErrorException;

    /**
     * <pre>
     * 获取订单状态
     * 详情请见: https://open.imdada.cn/wiki/getOrderInfo/
     * </pre>
     * @param orderId
     * @return
     * @throws DadaErrorException
     */
    DadaOrderInfoResp getOrderInfo(String orderId) throws DadaErrorException;

    /**
     * <pre>
     * 获取订单状态
     * 详情请见: https://open.imdada.cn/wiki/getDmInfo/
     * </pre>
     * @param dmId 配送员id(配送员接单后通过回调接口获得)
     * @param orderId 添加订单接口中的origin_id值
     * @return
     * @throws DadaErrorException
     */
    DadaDmInfo getDmInfo(int dmId, String orderId) throws DadaErrorException;

    /**
     * <pre>
     * 获取取消理由
     * 详情请见: https://open.imdada.cn/wiki/getCancelReasons
     * </pre>
     * @return
     * @throws DadaErrorException
     */
    List<DadaCancelReason> getCancelReasons() throws DadaErrorException;

    /**
     * <pre>
     * 在达达平台追加订单
     * 被追单的配送员必须在20分钟内接过此商家的订单，所以测试环境测试，请先调接单接口模拟接单，再使用号码15577777777追单
     * 详情请见: https://open.imdada.cn/wiki/appointNewOrder/
     * </pre>
     * @param appointNewOrderReq
     * @return orderid
     * @throws DadaErrorException
     */
    long appointNewOrder(DadaAppointNewOrderReq appointNewOrderReq) throws DadaErrorException;

    /**
     * <pre>
     * 在测试环境中，可调用此接口接受订单，检测回调
     * 详情请见: https://open.imdada.cn/wiki/acceptOrder/
     * </pre>
     * @param orderId
     * @throws DadaErrorException
     */
    void acceptOrder(String orderId) throws DadaErrorException;

    /**
     * <pre>
     * 在测试环境中，可调用此接口拒绝追单订单，检测回调
     * 详情请见: https://open.imdada.cn/wiki/rejectOrder/
     * </pre>
     *
     * @param orderId
     * @throws DadaErrorException
     */
    void rejectOrder(String orderId) throws DadaErrorException;
    /**
     * <pre>
     * 在测试环境中，可调用此接口完成取货，检测回调
     * 详情请见: https://open.imdada.cn/wiki/fetchOrder/
     * </pre>
     *
     * @param orderId
     * @throws DadaErrorException
     */
    void fetchOrder(String orderId) throws DadaErrorException;

    /**
     * <pre>
     * 订单已送达（仅在测试环境供调试使用）
     * 详情请见: https://open.imdada.cn/wiki/finishOrder/
     * </pre>
     * @param orderId
     * @throws DadaErrorException
     */
    void finishOrder(String orderId) throws DadaErrorException;

    /**
     * <pre>
     * 添加小费
     * 详情请见: https://open.imdada.cn/wiki/updateTips/
     * </pre>
     * @param req
     * @throws DadaErrorException
     */
    void updateTips(DadaUpdateTipsReq req) throws DadaErrorException;

    /**
     * <pre>
     * 获取城市信息列表
     * 详情请见: https://open.imdada.cn/wiki/cityList/
     * </pre>
     * @return
     * @throws DadaErrorException
     */
    List<DadaCity> getCities() throws DadaErrorException;

    void orderCallback(DadaOrderCallbackMessage callbackMessage);

    void setDadaConfigStorage(DadaConfigStorage dadaConfigProvider);
    void setDadaCallbackHandler(DadaCallbackHandler dadaCallbackHandler);
    void setTest(boolean test);
}

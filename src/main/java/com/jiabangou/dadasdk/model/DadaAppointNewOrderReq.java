package com.jiabangou.dadasdk.model;

/**
 * Created by freeway on 16/3/5.
 */
public class DadaAppointNewOrderReq {
    private String origin_id;	//第三方对接平台订单id
    private String city_name; //否	订单所在城市名 称(如上海就填”上海“，勿带上“市”)
    private String city_code;  //	是	订单所在城市的区号(参考链接)
    private String ad_code; //是	订单所在城市的行政区号(参考链接)
    private float pay_for_supplier_fee; //是	付给商家的费用, 如果无需支付,传0
    private float fetch_from_receiver_fee; //是	向客户收取的费 用,如果无,传0
    private float deliver_fee;//第三方平台补贴的运费金额, 金额由达达与第三方接入平台接入前共同讨论决定。如果无,传0


    private int create_time; //	第三方平台的原始订单创建时间戳
    private String info; //订单详细信息或者订单备注
    private int cargo_type; //订单商品类型 1、餐饮 2、饮 料 3、鲜花 4、票 务 5、其他 8、印刷品 9、便利店 10、学校餐饮 11、校园便利 12、生鲜 13、水果
    private float cargo_weight;	//订单商品重量,如果无,传为1
    private float cargo_price; //订单商品价格
    private int cargo_num; //商品份数
    private int is_prepay; //是否需要垫付 1:是 0:否
    private int expected_fetch_time; //期望取货时间,如果无,传0,传0的情况下，默认取当前时间10分钟之后为期望取货时间
    private int expected_finish_time; //	是	期望送达时间,如果无,传0
    private String supplier_name; //	string	是	发货人姓名,平台名-商家名
    private String supplier_address; //	string	是	发货地址
    private String supplier_phone; //	string	是	发货人手机号
    private String supplier_tel; //	string	是	发货人座机,不需要传区号，手机号和座机号 二者至少有一个
    private float supplier_lat; //	float	是	发货人(商家)纬度,如果无,传0.(坐标系为高德地图坐标系，又称火星坐标)
    private float supplier_lng; //	float	是	发货人(商家)经度,如果无,传0.(坐标系为高德地图坐标系，又称火星坐标)
    private String invoice_title; //	string	是	发票抬头(个人填 “个人”,公司填 公司名称),如果无,传空值
    private String receiver_name; //	string	是	收货人姓名,如果无,传空值
    private String receiver_address; //	string	是	收货人地址
    private String receiver_phone; //	string	是	收货人手机
    private String receiver_tel; //	string	是	收货人座机, 不需要传区号，手机号和座机号 二者至少有一个
    private float receiver_lat; //	float	是	收货人地址纬度, 如果无,传0.(坐标系为高德地图坐标系，又称火星坐标)
    private float receiver_lng; //	float	是	收货人地址经度, 如果无,传0.(坐标系为高德地图坐标系，又称火星坐标)
    private String callback; //	string	是	回调URL
    private int transporter_phone; //	int	是	配送员电话，测试环境请使用15577777777测试

    public String getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(String origin_id) {
        this.origin_id = origin_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getAd_code() {
        return ad_code;
    }

    public void setAd_code(String ad_code) {
        this.ad_code = ad_code;
    }

    public float getPay_for_supplier_fee() {
        return pay_for_supplier_fee;
    }

    public void setPay_for_supplier_fee(float pay_for_supplier_fee) {
        this.pay_for_supplier_fee = pay_for_supplier_fee;
    }

    public float getFetch_from_receiver_fee() {
        return fetch_from_receiver_fee;
    }

    public void setFetch_from_receiver_fee(float fetch_from_receiver_fee) {
        this.fetch_from_receiver_fee = fetch_from_receiver_fee;
    }

    public float getDeliver_fee() {
        return deliver_fee;
    }

    public void setDeliver_fee(float deliver_fee) {
        this.deliver_fee = deliver_fee;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCargo_type() {
        return cargo_type;
    }

    public void setCargo_type(int cargo_type) {
        this.cargo_type = cargo_type;
    }

    public float getCargo_weight() {
        return cargo_weight;
    }

    public void setCargo_weight(float cargo_weight) {
        this.cargo_weight = cargo_weight;
    }

    public float getCargo_price() {
        return cargo_price;
    }

    public void setCargo_price(float cargo_price) {
        this.cargo_price = cargo_price;
    }

    public int getCargo_num() {
        return cargo_num;
    }

    public void setCargo_num(int cargo_num) {
        this.cargo_num = cargo_num;
    }

    public int getIs_prepay() {
        return is_prepay;
    }

    public void setIs_prepay(int is_prepay) {
        this.is_prepay = is_prepay;
    }

    public int getExpected_fetch_time() {
        return expected_fetch_time;
    }

    public void setExpected_fetch_time(int expected_fetch_time) {
        this.expected_fetch_time = expected_fetch_time;
    }

    public int getExpected_finish_time() {
        return expected_finish_time;
    }

    public void setExpected_finish_time(int expected_finish_time) {
        this.expected_finish_time = expected_finish_time;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public String getSupplier_tel() {
        return supplier_tel;
    }

    public void setSupplier_tel(String supplier_tel) {
        this.supplier_tel = supplier_tel;
    }

    public float getSupplier_lat() {
        return supplier_lat;
    }

    public void setSupplier_lat(float supplier_lat) {
        this.supplier_lat = supplier_lat;
    }

    public float getSupplier_lng() {
        return supplier_lng;
    }

    public void setSupplier_lng(float supplier_lng) {
        this.supplier_lng = supplier_lng;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_tel() {
        return receiver_tel;
    }

    public void setReceiver_tel(String receiver_tel) {
        this.receiver_tel = receiver_tel;
    }

    public float getReceiver_lat() {
        return receiver_lat;
    }

    public void setReceiver_lat(float receiver_lat) {
        this.receiver_lat = receiver_lat;
    }

    public float getReceiver_lng() {
        return receiver_lng;
    }

    public void setReceiver_lng(float receiver_lng) {
        this.receiver_lng = receiver_lng;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public int getTransporter_phone() {
        return transporter_phone;
    }

    public void setTransporter_phone(int transporter_phone) {
        this.transporter_phone = transporter_phone;
    }

    @Override
    public String toString() {
        return "DadaAppointNewOrderReq{" +
                "origin_id='" + origin_id + '\'' +
                ", city_name='" + city_name + '\'' +
                ", city_code='" + city_code + '\'' +
                ", ad_code='" + ad_code + '\'' +
                ", pay_for_supplier_fee=" + pay_for_supplier_fee +
                ", fetch_from_receiver_fee=" + fetch_from_receiver_fee +
                ", deliver_fee=" + deliver_fee +
                ", create_time=" + create_time +
                ", info='" + info + '\'' +
                ", cargo_type=" + cargo_type +
                ", cargo_weight=" + cargo_weight +
                ", cargo_price=" + cargo_price +
                ", cargo_num=" + cargo_num +
                ", is_prepay=" + is_prepay +
                ", expected_fetch_time=" + expected_fetch_time +
                ", expected_finish_time=" + expected_finish_time +
                ", supplier_name='" + supplier_name + '\'' +
                ", supplier_address='" + supplier_address + '\'' +
                ", supplier_phone='" + supplier_phone + '\'' +
                ", supplier_tel='" + supplier_tel + '\'' +
                ", supplier_lat=" + supplier_lat +
                ", supplier_lng=" + supplier_lng +
                ", invoice_title='" + invoice_title + '\'' +
                ", receiver_name='" + receiver_name + '\'' +
                ", receiver_address='" + receiver_address + '\'' +
                ", receiver_phone='" + receiver_phone + '\'' +
                ", receiver_tel='" + receiver_tel + '\'' +
                ", receiver_lat=" + receiver_lat +
                ", receiver_lng=" + receiver_lng +
                ", callback='" + callback + '\'' +
                ", transporter_phone=" + transporter_phone +
                '}';
    }
}

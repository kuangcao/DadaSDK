package com.jiabangou.dadasdk.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.jiabangou.dadasdk.exception.DadaErrorException;
import com.jiabangou.dadasdk.model.DadaAddOrderReq;
import com.jiabangou.dadasdk.model.DadaAddOrderResp;
import org.junit.BeforeClass;
import org.junit.Test;

public class DadaServiceTest {

    private static DadaService dadaService;

    @BeforeClass
    public static void setUp() throws DadaErrorException {
        dadaService = new DadaServiceImpl();
        dadaService.setTest(true);
        DadaInMemoryConfigStorage configStorage = new DadaInMemoryConfigStorage();
        configStorage.setAppKey("xxxx");
        dadaService.setDadaConfigStorage(configStorage);
        String accessToken = dadaService.getAccessToken();
    }

    @Test
    public void testAddOrder() throws DadaErrorException {
        DadaAddOrderReq req = new DadaAddOrderReq();
        req.setCallback("http://wewe.sdsd.com");
        req.setCargo_num(10);
        req.setCargo_price(20);
        req.setCargo_weight(1);
        req.setIs_prepay(0);
        req.setCity_code("010");
        req.setCity_name("北京");
        req.setDeliver_fee(20);
        req.setCargo_type(1);
        req.setExpected_fetch_time(0);
        req.setExpected_finish_time(0);
        req.setSupplier_address("北京");
        req.setSupplier_id("0");
        req.setSupplier_phone("18657812771");
        req.setSupplier_tel("121212");
        req.setSupplier_name("huwei");
        req.setPay_for_supplier_fee(23);
        req.setCreate_time(0);
        req.setFetch_from_receiver_fee(0);
        req.setInfo("备注");
        req.setInvoice_title("hhaha");
        req.setOrigin_id("234234234");
        req.setTips(1);
        req.setReceiver_address("sdfsfsdf");
        req.setReceiver_phone("18657128771");
        req.setReceiver_lat(1231231.23423f);
        req.setReceiver_lng(31.23423f);
        req.setReceiver_name("sdfs");
        req.setReceiver_tel("sdfsdfs");

        DadaAddOrderResp addOrderResp = dadaService.addOrder(req);

        System.out.println(addOrderResp);
    }

    @Test
    public void testGetCancelReasons() throws DadaErrorException {
        System.out.println(dadaService.getCancelReasons());
    }

    @Test
    public void testGetCities() throws DadaErrorException {
        System.out.println(dadaService.getCities());
    }

}

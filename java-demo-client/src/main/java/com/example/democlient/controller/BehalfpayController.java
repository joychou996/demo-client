package com.example.democlient.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.democlient.domain.BizBehalfpay;
import com.example.democlient.domain.CreateOrder;
import com.example.democlient.domain.EncryptData;
import com.example.democlient.domain.ResponseBodyVo;
import com.example.democlient.service.BehalfpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class BehalfpayController {


    /**
     * 商户编号
     */
    @Value("${demo.custCode}")
    String custCode = "";

    @Autowired
    BehalfpayService behalfpayService;

    /**
     * 创建代付订单接口
     *
     * @return
     */
    @PostMapping("/behalfpay/create")
    public ResponseBodyVo CreateBehalfpayOrder(@RequestParam("m") BigDecimal money,@RequestParam("tag") String tag) {

        BizBehalfpay createOrder = new BizBehalfpay();
        createOrder.setCustCode(custCode);
        createOrder.setCustCashTag(tag);
        //你的订单编号
        createOrder.setCustOrderId(RandomUtil.randomString(16).toUpperCase());
        //订单金额
        createOrder.setBehalfpayAmount(money);
        createOrder.setBehalfpayAmount(createOrder.getBehalfpayAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        return behalfpayService.CreateBehalfPayOrder(createOrder);
    }


    /**
     * 订单回调接口
     *
     * @return
     */
    @PostMapping("/behalfpay/callback")
    public ResponseBodyVo BehalfpayallBack(@RequestBody EncryptData data) {
        ResponseBodyVo bodyVo = new ResponseBodyVo();
        bodyVo.setData(behalfpayService.BehalfPayCallBack(data));
        return bodyVo;
    }

}

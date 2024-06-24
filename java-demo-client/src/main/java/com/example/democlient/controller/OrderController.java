package com.example.democlient.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.democlient.domain.BizOrder;
import com.example.democlient.domain.CreateOrder;
import com.example.democlient.domain.EncryptData;
import com.example.democlient.domain.ResponseBodyVo;
import com.example.democlient.service.OrderService;
import com.example.democlient.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 接口
 */
@RestController
public class OrderController {


    @Autowired
    OrderService orderService;

    /**
     * 商户编号
     */
    @Value("${demo.custCode}")
    String custCode = "";

    /**
     * 创建订单接口
     *
     * @return
     */
    @PostMapping("/create")
    public ResponseBodyVo CreateOrder() {

        CreateOrder createOrder = new CreateOrder();
        createOrder.setCustCode(custCode);
        //单位
        createOrder.setUnit("USD");
        //你的订单编号
        createOrder.setCustOrderId(RandomUtil.randomString(16).toUpperCase());
        //订单金额
        createOrder.setOrderAmount(new BigDecimal(19.9));
        createOrder.setOrderAmount(createOrder.getOrderAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        return orderService.CreateOrder(createOrder);
    }


    /**
     * 订单回调接口
     *
     * @return
     */
    @PostMapping("/callback")
    public ResponseBodyVo OrderCallBack(@RequestBody EncryptData data) {
        ResponseBodyVo bodyVo = new ResponseBodyVo();
        bodyVo.setData(orderService.OrderCallBack(data));
        return bodyVo;
    }
}

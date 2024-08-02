package com.example.democlient.service;

import cn.hutool.core.util.RandomUtil;
import com.example.democlient.domain.CreateOrder;
import com.example.democlient.domain.ResponseBodyVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder() {

        CreateOrder createOrder = new CreateOrder();
        createOrder.setCustCode("xxxx");
        //单位
        createOrder.setUnit("USD");
        //你的订单编号
        createOrder.setCustOrderId("202407311509127276918");
        //订单金额
        createOrder.setOrderAmount(new BigDecimal(100));
        createOrder.setOrderAmount(createOrder.getOrderAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        ResponseBodyVo vo = orderService.CreateOrder(createOrder);

        System.out.println(vo);
    }
}
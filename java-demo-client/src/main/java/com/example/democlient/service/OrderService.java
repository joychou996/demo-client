package com.example.democlient.service;

import com.alibaba.fastjson2.JSON;
import com.example.democlient.domain.BizOrder;
import com.example.democlient.domain.CreateOrder;
import com.example.democlient.domain.EncryptData;
import com.example.democlient.domain.ResponseBodyVo;
import com.example.democlient.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderService {

    @Value("${demo.publicKey}")
    String publicKey = "";

    @Value("${demo.payUrl}")
    String payUrl = "";

    @Autowired
    RestTemplate restTemplate;

    public ResponseBodyVo CreateOrder(CreateOrder orderVo) {

        ResponseBodyVo bodyVo = new ResponseBodyVo();
        //加密
        String data = RsaUtils.EncryptByPublicKey(JSON.toJSONString(orderVo), publicKey);
        EncryptData encryptData = new EncryptData();
        encryptData.setData(data);
        encryptData.setCustCode(orderVo.getCustCode());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Content-Type", "application/json");
        String sss = JSON.toJSONString(encryptData);
        HttpEntity<String> entity = new HttpEntity<>(sss, httpHeaders);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(payUrl, entity, String.class);
        // 获取响应结果
        HttpStatus statusCode = postForEntity.getStatusCode();
        if (statusCode.value() == HttpStatus.OK.value()) {
            String body = postForEntity.getBody();
            bodyVo = JSON.parseObject(body, ResponseBodyVo.class);
            if (bodyVo.getCode().equals(200)) {
                BizOrder order = JSON.parseObject(JSON.toJSONString(bodyVo.getData()), BizOrder.class);
                bodyVo.setData(order);
            }
        } else {
            bodyVo.setCode(statusCode.value());
            bodyVo.setMsg(postForEntity.getBody());
        }

        return bodyVo;

    }

    public Boolean OrderCallBack(EncryptData data) {
        //解密
        String dataStr = RsaUtils.DecryptByPublicKey(data.getData(), publicKey);

        System.out.println(dataStr);

        BizOrder order = JSON.parseObject(dataStr, BizOrder.class);
        if (order == null) {
            throw new RuntimeException("数据不正确");
        }

        if (order.getCustOrderId() == null) {
            throw new RuntimeException("订单为空");
        }

        //如果ID是test,请直接返回成功，用作测试
        if (order.getCustOrderId().equalsIgnoreCase("test")) {
            return true;
        }

        //todo 到数据库里对比，更新自已的订单，前端刷新
        //为了保证接口幂等性，如果订单已经处理，也请直接返回成功


        return true;
    }
}

package com.example.democlient.service;

import com.alibaba.fastjson2.JSON;
import com.example.democlient.domain.*;
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
public class BehalfpayService {

    @Value("${demo.publicKey}")
    String publicKey = "";

    @Value("${demo.behalfpayUrl}")
    String payUrl = "";

    @Autowired
    RestTemplate restTemplate;

    public ResponseBodyVo CreateBehalfPayOrder(BizBehalfpay orderVo) {

        ResponseBodyVo bodyVo = new ResponseBodyVo();
        //加密
        String data = RsaUtils.EncryptByPublicKey(JSON.toJSONString(orderVo), publicKey);
        EncryptData encryptData = new EncryptData();
        encryptData.setData(data);
        encryptData.setCustCode(orderVo.getCustCode());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(encryptData), httpHeaders);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(payUrl, entity, String.class);
        // 获取响应结果
        HttpStatus statusCode = postForEntity.getStatusCode();
        if (statusCode.value() == HttpStatus.OK.value()) {
            String body = postForEntity.getBody();
            bodyVo = JSON.parseObject(body, ResponseBodyVo.class);
            if (bodyVo.getCode().equals(200)) {
                BizBehalfpay order = JSON.parseObject(JSON.toJSONString(bodyVo.getData()), BizBehalfpay.class);
                bodyVo.setData(order);
            }
        } else {
            bodyVo.setCode(statusCode.value());
            bodyVo.setMsg(postForEntity.getBody());
        }

        return bodyVo;

    }

    public Boolean BehalfPayCallBack(EncryptData data) {
        //解密
        String dataStr = RsaUtils.DecryptByPublicKey(data.getData(), publicKey);

        System.out.println(dataStr);

        BizBehalfpay order = JSON.parseObject(dataStr, BizBehalfpay.class);
        if (order == null) {
            throw new RuntimeException("数据不正确");
        }

        if (order.getCustOrderId() == null) {
            throw new RuntimeException("订单为空");
        }

        //如果ID是test,请直接返回成功，用作测试
        if(order.getCustOrderId().equalsIgnoreCase("test")){
            return true;
        }

        //todo 到数据库里对比，更新自已的订单，前端刷新

        return true;
    }
}

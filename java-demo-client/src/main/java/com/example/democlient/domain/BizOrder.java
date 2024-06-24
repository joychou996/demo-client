package com.example.democlient.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BizOrder {

    /**
     * 系统订单编号
     */
    private String orderId;


    /**
     * 客户编号
     */
    private String custCode;

    /**
     * 客户系统订单编号
     */
    private String custOrderId;

    /**
     * 创建时间
     */
    private Date createAt;


    /**
     * 单位
     */
    private String unit;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;


    /**
     * 支付地址
     */
    private String payUrl;

}

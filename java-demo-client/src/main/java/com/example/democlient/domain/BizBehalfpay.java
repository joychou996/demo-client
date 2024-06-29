package com.example.democlient.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BizBehalfpay {

    /**
     * 代付金额
     */
    private BigDecimal behalfpayAmount;
    private String custCode;
    /**
     * 客户订单编号
     */
    private String custOrderId;

    /**
     * 收款的用户标签
     */
    private String custCashTag;

    /**
     * 1，成功，2失败
     */
    private Integer status;

    /**
     * 付款的Cash标签
     */
    private String channelCode;

}

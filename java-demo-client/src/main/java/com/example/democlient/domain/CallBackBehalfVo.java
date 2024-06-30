package com.example.democlient.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author github
 * @date 2024/6/28 15:22
 */
@Data
public class CallBackBehalfVo {

    String custOrderId;
    BigDecimal behalfpayAmount;
    Date payTime;
    Boolean isPass;
    String noPassReason;

}

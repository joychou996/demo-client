package com.example.democlient.domain;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrder {

    private String custCode;
    private String custOrderId;
    private String unit;
    private BigDecimal orderAmount;

    String player;

}

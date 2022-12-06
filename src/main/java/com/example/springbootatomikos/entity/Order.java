package com.example.springbootatomikos.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Order {

    private BigInteger id;

    private String user_id;

    private String commodity_code;

    private int count;

    private int money;

}

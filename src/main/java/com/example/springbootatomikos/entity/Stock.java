package com.example.springbootatomikos.entity;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Stock {

    private BigInteger id;

    private String commodity_code;

    private int count;

}

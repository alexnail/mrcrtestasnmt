package com.alexnail.mrcrtestasnmt.models;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExchangeModel {

    private String from;

    private String to;

    private BigDecimal exchange;
}

package com.alexnail.mrcrtestasnmt.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class FixerResponseModel {

    private Boolean success;

    private LocalDateTime timestamp;

    private String base;

    private LocalDate date;

    List<ExchangeRate> rates;

    @Data
    public static class ExchangeRate {

        private String symbol;

        private BigDecimal rate;
    }
}

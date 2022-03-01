package com.alexnail.mrcrtestasnmt.repositories;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpreadRepository {

    @Value("${exchangerate.remote.service.base-currency}")
    private String baseCurrency;

    public BigDecimal get(String currency) {
        if (baseCurrency.equals(currency))
            return BigDecimal.ZERO;

        switch (currency) {
            case "USD":
                return BigDecimal.ONE;
            case "JPY":
            case "HKD":
            case "KRW":
                return BigDecimal.valueOf(3.25);
            case "MYR":
            case "INR":
            case "MXN":
                return BigDecimal.valueOf(4.5);
            case "RUB":
            case "CNY":
            case "ZAR":
                return BigDecimal.valueOf(6);
            default:
                return BigDecimal.valueOf(2.75);
        }
    }
}

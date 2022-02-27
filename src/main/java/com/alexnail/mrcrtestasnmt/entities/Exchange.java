package com.alexnail.mrcrtestasnmt.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Entity
@Data
@IdClass(ExchangeId.class)
public class Exchange {

    @Id
    private String currencyFrom;

    @Id
    private String currencyTo;

    @Id
    private LocalDate date;

    private BigDecimal exchangeRate;

    private Long requestsCount;

}

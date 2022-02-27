package com.alexnail.mrcrtestasnmt.entities;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeId implements Serializable {

    private String currencyFrom;

    private String currencyTo;

    private LocalDate date;
}

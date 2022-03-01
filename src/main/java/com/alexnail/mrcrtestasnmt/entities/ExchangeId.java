package com.alexnail.mrcrtestasnmt.entities;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeId implements Serializable {

    private String currencyFrom;

    private String currencyTo;

    private LocalDate date;
}

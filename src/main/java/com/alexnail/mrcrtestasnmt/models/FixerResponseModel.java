package com.alexnail.mrcrtestasnmt.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FixerResponseModel {

    private Boolean success;

    private Long timestamp;

    private String base;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Map<String, BigDecimal> rates;
}

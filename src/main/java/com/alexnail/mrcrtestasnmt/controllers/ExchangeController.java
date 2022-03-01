package com.alexnail.mrcrtestasnmt.controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.services.ExternalRatesScheduler;
import com.alexnail.mrcrtestasnmt.services.api.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    private final ExternalRatesScheduler externalRatesScheduler;

    public ExchangeController(ExchangeService exchangeService, ExternalRatesScheduler externalRatesScheduler) {
        this.exchangeService = exchangeService;
        this.externalRatesScheduler = externalRatesScheduler;
    }

    @GetMapping
    public ExchangeModel exchange(@RequestParam("from") String from,
                                  @RequestParam("to") String to,
                                  @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        return exchangeService.exchange(from, to, date);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getLatestRates() {
         externalRatesScheduler.downloadAndSaveRates();
    }
}

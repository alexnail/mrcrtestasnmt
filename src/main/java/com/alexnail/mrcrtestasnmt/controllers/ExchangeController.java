package com.alexnail.mrcrtestasnmt.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.services.api.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public ExchangeModel exchange(@RequestParam("from") String from,
                                  @RequestParam("to") String to,
                                  @RequestParam("date") LocalDate date) {
        return exchangeService.exchange(from, to, date);
    }
}

package com.alexnail.mrcrtestasnmt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class ExternalRatesScheduler {

    private ExternalRatesClient ratesClient;

    private ExchangeRepository exchangeRepository;

    @Scheduled(cron = "0 5 0 * * *",zone = "GMT")
    public void downloadAndSaveRates() {
        FixerResponseModel response = ratesClient.latest();
        if (!response.getSuccess()) {
            log.error("Failed to get latest rates.");
        } else {
            List<Exchange> exchangeList = new ArrayList<>();

            response.getRates().forEach(er -> {
                Exchange exchange = new Exchange();
                exchange.setCurrencyFrom(response.getBase());
                exchange.setCurrencyTo(er.getSymbol());
                exchange.setExchangeRate(er.getRate());
                exchange.setDate(response.getDate());
                exchangeList.add(exchange);
            });

            exchangeRepository.saveAll(exchangeList);
            log.info("Successfully downloaded latest rates.");
        }
    }
}

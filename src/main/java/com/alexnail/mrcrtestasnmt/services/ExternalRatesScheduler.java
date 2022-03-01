package com.alexnail.mrcrtestasnmt.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.mappers.ExchangeMapper;
import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExternalRatesScheduler {

    @Autowired
    private ExternalRatesClient ratesClient;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Scheduled(cron = "0 5 0 * * *",zone = "GMT")
    public void downloadAndSaveRates() {
        FixerResponseModel response = ratesClient.getLatestRates();
        if (!response.getSuccess()) {
            log.error("Failed to get latest rates.");
        } else {
            List<Exchange> exchangeList = new ArrayList<>();
            response.getRates().forEach((k, v) -> exchangeList.add(exchangeMapper.toEntity(response, k, v)));
            exchangeRepository.saveAll(exchangeList);
            log.info("Successfully downloaded latest rates.");
        }
    }
}

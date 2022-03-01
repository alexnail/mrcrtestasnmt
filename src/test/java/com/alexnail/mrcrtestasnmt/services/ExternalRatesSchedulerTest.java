package com.alexnail.mrcrtestasnmt.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alexnail.mrcrtestasnmt.mappers.ExchangeMapper;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;

@ExtendWith(SpringExtension.class)
class ExternalRatesSchedulerTest {

    @Autowired
    private ExternalRatesScheduler externalRatesScheduler;

    @MockBean
    ExchangeRepository exchangeRepository;

    @MockBean
    ExchangeMapper exchangeMapper;


    @Test
    void testGetRates() {
        externalRatesScheduler.downloadAndSaveRates();
    }

    @TestConfiguration
    public static class IntegrationTestConfiguration {

        @Bean
        ExternalRatesClient externalRatesClient() {
            return new FixerClient();
        }

        @Bean
        ExternalRatesScheduler externalRatesScheduler() {
            return new ExternalRatesScheduler();
        }
    }
}
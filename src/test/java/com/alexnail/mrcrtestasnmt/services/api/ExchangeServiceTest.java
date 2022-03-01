package com.alexnail.mrcrtestasnmt.services.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.entities.ExchangeId;
import com.alexnail.mrcrtestasnmt.mappers.ExchangeMapper;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;
import com.alexnail.mrcrtestasnmt.repositories.SpreadRepository;

@ExtendWith(SpringExtension.class)
class ExchangeServiceTest {

    @Autowired
    ExchangeService exchangeService;

    @MockBean
    ExchangeRepository exchangeRepository;

    @MockBean
    ExchangeMapper exchangeMapper;

    @Test
    void testExchange() {
        LocalDate date = LocalDate.of(2020, 12, 5);
        ExchangeId idFrom = new ExchangeId("USD", "EUR", date);
        Exchange exchangeFrom = getExchange("USD", "EUR", date, 0.8);
        ExchangeModel modelFrom = getExchangeModel("USD", "EUR", 0.8);
        when(exchangeRepository.getById(idFrom)).thenReturn(exchangeFrom);
        when(exchangeMapper.toModel(exchangeFrom)).thenReturn(modelFrom);

        ExchangeId idTo = new ExchangeId("USD", "PLN", date);
        Exchange exchangeTo = getExchange("USD", "PLN", date, 3.7);
        ExchangeModel modelTo = getExchangeModel("USD", "PLN", 3.7);
        when(exchangeRepository.getById(idTo)).thenReturn(exchangeTo);
        when(exchangeMapper.toModel(exchangeTo)).thenReturn(modelTo);

        when(exchangeRepository.getById(any(ExchangeId.class))).thenReturn(exchangeFrom).thenReturn(exchangeTo);

        ExchangeModel exchange = exchangeService.exchange("EUR", "PLN", date);
        //assertNotNull(exchange);
    }

    private Exchange getExchange(String from, String to, LocalDate date, double rate) {
        Exchange exchange = new Exchange();
        exchange.setCurrencyFrom(from);
        exchange.setCurrencyTo(to);
        exchange.setDate(date);
        exchange.setExchangeRate(BigDecimal.valueOf(rate));
        return exchange;
    }

    private ExchangeModel getExchangeModel(String from, String to, double exchange) {
        ExchangeModel modelTo = new ExchangeModel();
        modelTo.setFrom(from);
        modelTo.setTo(to);
        modelTo.setExchange(BigDecimal.valueOf(exchange));
        return modelTo;
    }

    @TestConfiguration
    public static class IntegrationTestConfiguration {
        @Bean
        ExchangeService exchangeService() {
            return new ExchangeService();
        }

        @Bean
        SpreadRepository spreadRepository() {
            return new SpreadRepository();
        }
    }

}
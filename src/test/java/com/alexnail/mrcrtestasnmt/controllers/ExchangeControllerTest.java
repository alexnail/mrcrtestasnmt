package com.alexnail.mrcrtestasnmt.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.services.ExternalRatesScheduler;
import com.alexnail.mrcrtestasnmt.services.api.ExchangeService;

@WebMvcTest
class ExchangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExchangeService exchangeService;

    @MockBean
    private ExternalRatesScheduler externalRatesScheduler;


    @Test
    void testExchange() throws Exception {
        ExchangeModel exchangeModel = new ExchangeModel();
        exchangeModel.setFrom("EUR");
        exchangeModel.setTo("PLN");
        exchangeModel.setExchange(BigDecimal.ONE);
        when(exchangeService.exchange(eq("EUR"), eq("PLN"), any(LocalDate.class)))
                .thenReturn(exchangeModel);

        mvc.perform(get("/exchange")
                            .param("from", "EUR")
                            .param("to", "PLN")
                            .param("date", "2020-12-05"))
           .andExpect(status().isOk())
                .andExpect(jsonPath("$.from", is("EUR")))
                .andExpect(jsonPath("$.to", is("PLN")))
                .andExpect(jsonPath("$.exchange", is(1)));
    }

    @Test
    void testExchangeNotFound() throws Exception {
        when(exchangeService.exchange(eq("EUR"), eq("PLN"), any(LocalDate.class)))
                .thenThrow(EntityNotFoundException.class);

        mvc.perform(get("/exchange")
                            .param("from", "EUR")
                            .param("to", "PLN")
                            .param("date", "2020-12-05"))
           .andExpect(status().isNotFound());
    }
}
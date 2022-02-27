package com.alexnail.mrcrtestasnmt.services.api;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.entities.ExchangeId;
import com.alexnail.mrcrtestasnmt.mappers.ExchangeMapper;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExchangeService {

    private ExchangeRepository exchangeRepository;

    private ExchangeMapper exchangeMapper;

    public ExchangeModel exchange(String from, String to, LocalDate date) {
        Exchange exchange = exchangeRepository.getById(new ExchangeId(from, to, date));
        exchangeRepository.incrementRequestsCounter(from, to, date);
        return exchangeMapper.toModel(exchange);
    }
}

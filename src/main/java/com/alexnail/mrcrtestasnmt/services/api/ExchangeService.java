package com.alexnail.mrcrtestasnmt.services.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.entities.ExchangeId;
import com.alexnail.mrcrtestasnmt.mappers.ExchangeMapper;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.repositories.ExchangeRepository;
import com.alexnail.mrcrtestasnmt.repositories.SpreadRepository;

@Service
public class ExchangeService {

    @Value("${exchangerate.remote.service.base-currency}")
    private String baseCurrency;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private SpreadRepository spreadRepository;

    @Autowired
    private ExchangeMapper exchangeMapper;

    public ExchangeModel exchange(String from, String to, LocalDate date) {
        Exchange exchangeFrom = exchangeRepository.getById(new ExchangeId(baseCurrency, from, date));
        exchangeRepository.incrementRequestsCounter(baseCurrency, from, date);
        Exchange exchangeTo = exchangeRepository.getById(new ExchangeId(baseCurrency, to, date));
        exchangeRepository.incrementRequestsCounter(baseCurrency, to, date);

        Exchange result = new Exchange();
        result.setCurrencyFrom(from);
        result.setCurrencyTo(to);
        result.setDate(date);
        result.setExchangeRate(calculateExchange(exchangeFrom.getExchangeRate(),
                                                 exchangeTo.getExchangeRate(),
                                                 spreadRepository.get(from),
                                                 spreadRepository.get(to)));
        return exchangeMapper.toModel(result);
    }

    private BigDecimal calculateExchange(BigDecimal rateFrom, BigDecimal rateTo, BigDecimal spreadFrom, BigDecimal spreadTo) {
        return rateTo.divide(rateFrom, RoundingMode.HALF_UP)
                     .multiply(getSpreadRate(spreadFrom, spreadTo));
    }

    private BigDecimal getSpreadRate(BigDecimal spreadFrom, BigDecimal spreadTo) {
        BigDecimal hundred = BigDecimal.valueOf(100);
        return hundred.subtract(spreadFrom.max(spreadTo))
                      .divide(hundred, RoundingMode.HALF_UP);
    }
}

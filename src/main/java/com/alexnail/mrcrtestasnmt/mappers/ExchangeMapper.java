package com.alexnail.mrcrtestasnmt.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;

@Mapper(componentModel = "spring")
public interface ExchangeMapper {

    @Mapping(target = "from", source = "currencyFrom")
    @Mapping(target = "to", source = "currencyTo")
    @Mapping(target = "exchange", source = "exchangeRate")
    ExchangeModel toModel(Exchange exchange);

    @Mapping(target = "currencyFrom", source = "response.base")
    @Mapping(target = "currencyTo", source = "symbol")
    @Mapping(target = "exchangeRate", source = "rate")
    @Mapping(target = "date", source = "response.date")
    Exchange toEntity(FixerResponseModel response, String symbol, BigDecimal rate);
}

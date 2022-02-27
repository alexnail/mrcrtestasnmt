package com.alexnail.mrcrtestasnmt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;

@Mapper
public interface ExchangeMapper {

    @Mapping(target = "from", source = "currencyFrom")
    @Mapping(target = "to", source = "currencyTo")
    @Mapping(target = "exchange", source = "exchangeRate")
    ExchangeModel toModel(Exchange exchange);
}

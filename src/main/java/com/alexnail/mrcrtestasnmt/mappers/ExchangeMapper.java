package com.alexnail.mrcrtestasnmt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alexnail.mrcrtestasnmt.entities.Exchange;
import com.alexnail.mrcrtestasnmt.models.ExchangeModel;
import com.alexnail.mrcrtestasnmt.models.FixerResponseModel;

@Mapper
public interface ExchangeMapper {

    @Mapping(target = "from", source = "currencyFrom")
    @Mapping(target = "to", source = "currencyTo")
    @Mapping(target = "exchange", source = "exchangeRate")
    ExchangeModel toModel(Exchange exchange);

    @Mapping(target = "currencyFrom", source = "response.base")
    @Mapping(target = "currencyTo", source = "er.symbol")
    @Mapping(target = "exchangeRate", source = "er.rate")
    @Mapping(target = "date", source = "response.date")
    Exchange toEntity(FixerResponseModel response, FixerResponseModel.ExchangeRate er);
}

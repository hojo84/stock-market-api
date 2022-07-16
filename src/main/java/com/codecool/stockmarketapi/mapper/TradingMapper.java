package com.codecool.stockmarketapi.mapper;

import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.entity.Trading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TradingMapper {

    @Mapping(target = "ticker", expression = "java(trading.getListing().getId())")
    TradeDTO toDto(Trading trading);

    List<TradeDTO> toDto(List<Trading> trades);
}
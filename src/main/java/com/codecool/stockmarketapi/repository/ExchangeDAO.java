package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.entity.TradingData;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeDAO extends GenericCrudDAO<Exchange> {

    Exchange getExchangeByMarketIdentifierCode(String marketIdentifierCode);

    List<Index> getAllIndicesByExchangeCode(String marketIdentifierCode);

    List<Stock> getAllStocksByExchangeCode(String marketIdentifierCode);

    Stock getStockByTickerOnGivenExchange(String marketIdentifierCode, String stockTicker);

    TradingData getTradingDataByStockTickerOnGivenExchangeAndDate(String marketIdentifierCode, String stockTicker, LocalDate tradingDay);
}

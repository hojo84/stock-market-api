package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.Exchange;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import com.codecool.stockmarketapi.model.TradingData;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeDAO extends GenericCrudDAO<Exchange> {

    Exchange getExchangeByMarketIdentifierCode(String marketIdentifierCode);

    List<Index> getAllIndicesByExchangeCode(String marketIdentifierCode);

    List<Stock> getAllStocksByExchangeCode(String marketIdentifierCode);

    Stock getStockByTickerOnGivenExchange(String marketIdentifierCode, String stockTicker);

    TradingData getTradingDataByStockTickerOnGivenExchangeAndDate(String marketIdentifierCode, String stockTicker, LocalDate tradingDay);
}

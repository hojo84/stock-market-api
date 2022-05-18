package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import com.codecool.stockmarketapi.model.TradingData;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeDAO {

    List<Index> getAllIndicesByExchangeId(String id);

    List<Stock> getAllStocksByExchangeId(String id);

    Stock getStockByIdOnGivenExchange(String exchangeId, String stockId);

    TradingData getTradingDataByStockIdOnGivenExchangeAndDate(String exchangeId, String stockId, LocalDate tradingDay);
}

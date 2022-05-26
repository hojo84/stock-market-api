package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.ExchangeDAO;
import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.model.Exchange;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import com.codecool.stockmarketapi.model.TradingData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeService {

    private GenericCrudDAO<Exchange> genericCrudDAO;
    private ExchangeDAO exchangeDAO;

    public List<Exchange> listAll() {
        return genericCrudDAO.listAll();
    }

    public Long save(Exchange exchange) {
        return genericCrudDAO.save(exchange);
    }

    public Exchange findById(Long id) {
        return genericCrudDAO.findById(id);
    }

    public void deleteById(Long id) {
        genericCrudDAO.deleteById(id);
    }

    public Exchange getExchangeByMarketIdentifierCode(String marketIdentifierCode) {
        return exchangeDAO.getExchangeByMarketIdentifierCode(marketIdentifierCode);
    }

    public List<Index> getAllIndicesByExchangeCode(String marketIdentifierCode) {
        return exchangeDAO.getAllIndicesByExchangeCode(marketIdentifierCode);
    }

    public List<Stock> getAllStocksByExchangeCode(String marketIdentifierCode) {
        return exchangeDAO.getAllStocksByExchangeCode(marketIdentifierCode);
    }

    public Stock getStockByTickerOnGivenExchange(String marketIdentifierCode, String stockTicker) {
        return exchangeDAO.getStockByTickerOnGivenExchange(marketIdentifierCode, stockTicker);
    }

    public TradingData getTradingDataByStockTickerOnGivenExchangeAndDate(String marketIdentifierCode, String stockTicker, LocalDate tradingDay) {
        return exchangeDAO.getTradingDataByStockTickerOnGivenExchangeAndDate(marketIdentifierCode, stockTicker, tradingDay);
    }
}

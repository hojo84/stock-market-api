package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.ExchangeDAO;
import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.model.Exchange;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import com.codecool.stockmarketapi.model.TradingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExchangeService {

    private final GenericCrudDAO<Exchange> genericCrudDAO;
    private final ExchangeDAO exchangeDAO;

    @Autowired
    public ExchangeService(GenericCrudDAO<Exchange> genericCrudDAO, ExchangeDAO exchangeDAO) {
        this.genericCrudDAO = genericCrudDAO;
        this.exchangeDAO = exchangeDAO;
    }

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

    public List<Index> getAllIndicesByExchangeId(String id) {
        return exchangeDAO.getAllIndicesByExchangeId(id);
    }

    public List<Stock> getAllStocksByExchangeId(String id) {
        return exchangeDAO.getAllStocksByExchangeId(id);
    }

    public Stock getStockByIdOnGivenExchange(String exchangeId, String stockId) {
        return exchangeDAO.getStockByIdOnGivenExchange(exchangeId, stockId);
    }

    public TradingData getTradingDataByStockIdOnGivenExchangeAndDate(String exchangeId, String stockId, LocalDate tradingDay) {
        return exchangeDAO.getTradingDataByStockIdOnGivenExchangeAndDate(exchangeId, stockId, tradingDay);
    }
}

package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {

    private ExchangeRepository exchangeRepository;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository) {
        this.exchangeRepository = exchangeRepository;
    }

    public List<Exchange> listAll() {
        return exchangeRepository.findAll();
    }

    public Exchange save(Exchange exchange) {
        return exchangeRepository.save(exchange);
    }

    public Optional<Exchange> findById(Long id) {
        return exchangeRepository.findById(id);
    }

    public void deleteById(Long id) {
        exchangeRepository.deleteById(id);
    }

    public Exchange getExchangeByMarketIdentifierCode(String marketIdentifierCode) {
        return exchangeRepository.findByMarketIdentifierCode(marketIdentifierCode);
    }

    public List<Stock> getAllStocksByExchangeCode(String marketIdentifierCode) {
        return exchangeRepository.getAllStocksByExchangeCode(marketIdentifierCode);
    }

    public Stock getStockByTickerOnGivenExchange(String marketIdentifierCode, String stockTicker) {
        return exchangeRepository.getStockByTickerOnGivenExchange(marketIdentifierCode, stockTicker);
    }
}

package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dto.StockDTO;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private StockRepository stockRepository;
    private ExchangeRepository exchangeRepository;

    @Autowired
    public StockService(StockRepository stockRepository, ExchangeRepository exchangeRepository) {
        this.stockRepository = stockRepository;
        this.exchangeRepository = exchangeRepository;
    }

    public List<String> listAll() {
        return stockRepository.findAllStockNames();
    }

    public Stock save(StockDTO stockDTO) {
        Stock stockToBeSaved = new Stock(stockDTO);
        return stockRepository.save(stockToBeSaved);
    }

    public Stock update(StockDTO stockDTO) {
        Stock stockToBeUpdated = new Stock(stockDTO);
        return stockRepository.save(stockToBeUpdated);
    }

    public Optional<Stock> findById(String id) {
        return stockRepository.findById(id);
    }

    public void deleteById(String id) {
        stockRepository.deleteById(id);
    }

    public void addStockToExchangeById(String stockId, String exchangeId) {
        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new IllegalArgumentException("Exchange does not exists: " + exchangeId));
        Stock stockToBeAdded = findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock does not exists: " + stockId));
        exchange.addStock(stockToBeAdded);
        exchangeRepository.save(exchange);
    }

    public void removeStockFromExchangeById(String stockId, String exchangeId) {
        Exchange exchange = exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new IllegalArgumentException("Exchange does not exists: " + exchangeId));
        Stock stockToBeRemoved = findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException("Stock does not exists: " + stockId));
        exchange.removeStock(stockToBeRemoved);
        exchangeRepository.save(exchange);
    }
}

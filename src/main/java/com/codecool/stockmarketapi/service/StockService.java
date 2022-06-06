package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> listAll() {
        return stockRepository.findAll();
    }

    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    public Optional<Stock> findById(String id) {
        return stockRepository.findById(id);
    }

    public void deleteById(String id) {
        stockRepository.deleteById(id);
    }

//    public Stock getStockByTicker(String ticker) {
//        return stockRepository.findByTickerSymbol(ticker);
//    }
}

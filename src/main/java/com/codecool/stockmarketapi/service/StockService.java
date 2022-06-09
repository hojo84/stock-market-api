package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dto.StockDTO;
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

    public List<String> listAll() {
        return stockRepository.findAllStockNames();
    }

    public Stock save(StockDTO stockDTO) {
        Stock stockToBeSaved = new Stock(stockDTO);
        return stockRepository.save(stockToBeSaved);
    }

    public Optional<Stock> findById(String id) {
        return stockRepository.findById(id);
    }

    public void deleteById(String id) {
        stockRepository.deleteById(id);
    }
}

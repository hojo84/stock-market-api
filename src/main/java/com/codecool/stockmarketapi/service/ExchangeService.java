package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeService {

    private ExchangeRepository exchangeRepository;
    private StockRepository stockRepository;

    @Autowired
    public ExchangeService(ExchangeRepository exchangeRepository, StockRepository stockRepository) {
        this.exchangeRepository = exchangeRepository;
        this.stockRepository = stockRepository;
    }

    public List<String> listAll() {
        return exchangeRepository.findAllExchangeNames();
    }

    public Exchange save(ExchangeDTO exchangeDTO) {
        Exchange exchangeToBeSaved = new Exchange(exchangeDTO);
        return exchangeRepository.save(exchangeToBeSaved);
    }

    public Optional<Exchange> findById(String id) {
        return exchangeRepository.findById(id);
    }

    public void deleteById(String id) {
        exchangeRepository.deleteById(id);
        stockRepository.deleteAllOrphanStocks();
    }

    public List<Stock> getAllStocksByExchangeId(String id) {
        return exchangeRepository.getAllStocksByExchangeId(id);
    }

    public Stock getStockByIdAndExchangeId(String exchangeId, String stockId) {
        return exchangeRepository.getStockByIdAndExchangeId(exchangeId, stockId);
    }
}

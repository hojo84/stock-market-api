package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.dao.StockDAO;
import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private GenericCrudDAO<Stock> genericCrudDAO;
    private StockDAO stockDAO;

    public List<Stock> listAll() {
        return genericCrudDAO.listAll();
    }

    public Long save(Stock stock) {
        return genericCrudDAO.save(stock);
    }

    public Stock findById(Long id) {
        return genericCrudDAO.findById(id);
    }

    public void deleteById(Long id) {
        genericCrudDAO.deleteById(id);
    }

    public Stock getStockByTicker(String ticker) {
        return stockDAO.getStockByTicker(ticker);
    }

    public List<Index> getAllIndicesContainingGivenStockByTicker(String ticker) {
        return stockDAO.getAllIndicesContainingGivenStockByTicker(ticker);
    }
}

package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.dao.StockDAO;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final GenericCrudDAO<Stock> genericCrudDAO;
    private final StockDAO stockDAO;

    @Autowired
    public StockService(GenericCrudDAO<Stock> genericCrudDAO, StockDAO stockDAO) {
        this.genericCrudDAO = genericCrudDAO;
        this.stockDAO = stockDAO;
    }

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

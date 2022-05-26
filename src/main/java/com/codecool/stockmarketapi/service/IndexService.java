package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.dao.IndexDAO;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.IndexComponent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    private GenericCrudDAO<Index> genericCrudDAO;
    private IndexDAO indexDAO;

    public List<Index> listAll() {
        return genericCrudDAO.listAll();
    }

    public Long save(Index index) {
        return genericCrudDAO.save(index);
    }

    public Index findById(Long id) {
        return genericCrudDAO.findById(id);
    }

    public void deleteById(Long id) {
        genericCrudDAO.deleteById(id);
    }

    public Index getIndexBySymbol(String symbol) {
        return indexDAO.getIndexBySymbol(symbol);
    }

    public List<IndexComponent> getAllComponentsByIndexSymbol(String symbol) {
        return indexDAO.getAllComponentsByIndexSymbol(symbol);
    }

    public void addStockToIndex(IndexComponent indexComponent) {
        indexDAO.addStockToIndex(indexComponent);
    }

    public void removeStockFromIndex(String stockTicker, String indexSymbol) {
        indexDAO.removeStockFromIndex(stockTicker, indexSymbol);
    }
}

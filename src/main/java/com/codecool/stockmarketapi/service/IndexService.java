package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dao.GenericCrudDAO;
import com.codecool.stockmarketapi.dao.IndexDAO;
import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.IndexComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    private final GenericCrudDAO<Index> genericCrudDAO;
    private final IndexDAO indexDAO;

    @Autowired
    public IndexService(GenericCrudDAO<Index> genericCrudDAO, IndexDAO indexDAO) {
        this.genericCrudDAO = genericCrudDAO;
        this.indexDAO = indexDAO;
    }

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

    public List<IndexComponent> getAllComponentsByIndexId(Long id) {
        return indexDAO.getAllComponentsByIndexId(id);
    }

    public void addStockToIndex(IndexComponent indexComponent) {
        indexDAO.addStockToIndex(indexComponent);
    }

    public void removeStockFromIndex(String stockId, Long indexId) {
        indexDAO.removeStockFromIndex(stockId, indexId);
    }
}
package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.IndexComponent;
import com.codecool.stockmarketapi.repository.IndexComponentRepository;
import com.codecool.stockmarketapi.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndexService {

    private IndexRepository indexRepository;
    private IndexComponentRepository indexComponentRepository;

    @Autowired
    public IndexService(IndexRepository indexRepository, IndexComponentRepository indexComponentRepository) {
        this.indexRepository = indexRepository;
        this.indexComponentRepository = indexComponentRepository;
    }

    public List<Index> listAll() {
        return indexRepository.findAll();
    }

    public Index save(Index index) {
        return indexRepository.save(index);
    }

    public Optional<Index> findById(Long id) {
        return indexRepository.findById(id);
    }

    public void deleteById(Long id) {
        indexRepository.deleteById(id);
    }

    public Index getIndexBySymbol(String symbol) {
        return indexRepository.findBySymbol(symbol);
    }

    public List<IndexComponent> getAllComponentsByIndexSymbol(String symbol) {
        return indexRepository.getAllComponentsByIndexSymbol(symbol);
    }

    public IndexComponent addStockToIndex(IndexComponent indexComponent) {
        return indexComponentRepository.save(indexComponent);
    }

    public void removeStockFromIndex(String stockTicker, String indexSymbol) {
        indexComponentRepository.deleteByStockTickerSymbolAndIndexSymbol(stockTicker, indexSymbol);
    }
}

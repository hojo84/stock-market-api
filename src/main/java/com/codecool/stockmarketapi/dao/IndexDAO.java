package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.IndexComponent;

import java.util.List;

public interface IndexDAO {

    List<IndexComponent> getAllComponentsByIndexId(Long id);

    void addStockToIndex(IndexComponent indexComponent);

    void removeStockFromIndex(String stockId, Long indexId);
}

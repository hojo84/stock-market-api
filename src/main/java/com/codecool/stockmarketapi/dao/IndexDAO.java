package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.IndexComponent;

import java.util.List;

public interface IndexDAO {

    Index getIndexBySymbol(String symbol);

    List<IndexComponent> getAllComponentsByIndexId(String symbol);

    void addStockToIndex(IndexComponent indexComponent);

    void removeStockFromIndex(String stockTicker, Long indexSymbol);
}

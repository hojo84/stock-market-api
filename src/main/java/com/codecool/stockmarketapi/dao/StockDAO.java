package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;

import java.util.List;

public interface StockDAO {

    Stock getStockByTicker(String ticker);

    List<Index> getAllIndicesContainingGivenStockByTicker(String ticker);
}

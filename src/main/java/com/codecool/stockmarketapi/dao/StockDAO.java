package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.Stock;

import java.util.List;

public interface StockDAO extends GenericCrudDAO<Stock> {

    Stock getStockByTicker(String ticker);

    List<Index> getAllIndicesContainingGivenStockByTicker(String ticker);
}

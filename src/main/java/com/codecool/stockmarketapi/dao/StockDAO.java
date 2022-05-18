package com.codecool.stockmarketapi.dao;

import com.codecool.stockmarketapi.model.Index;

import java.util.List;

public interface StockDAO {

    List<Index> getAllIndicesIncludingGivenStockById(String stockId);
}

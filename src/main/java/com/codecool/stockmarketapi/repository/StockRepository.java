package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAll();

    Stock save(Stock stock);

    Optional<Stock> findById(Long id);

    void deleteById(Long id);

    Stock findByTickerSymbol(String ticker);
}

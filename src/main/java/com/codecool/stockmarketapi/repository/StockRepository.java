package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    List<Stock> findAll();

    Stock save(Stock stock);

    Optional<Stock> findById(String id);

    @Modifying
    @Query("delete from Stock s where s.id=?1")
    void deleteById(String id);
}

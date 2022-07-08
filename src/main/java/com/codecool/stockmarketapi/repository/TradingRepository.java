package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Trading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradingRepository extends JpaRepository<Trading, Long> {
}
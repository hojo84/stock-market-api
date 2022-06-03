package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.IndexComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexComponentRepository extends JpaRepository<IndexComponent, Long> {

    IndexComponent save(IndexComponent indexComponent);

    void deleteByStockTickerSymbolAndIndexSymbol(String stockTicker, String indexSymbol);
}

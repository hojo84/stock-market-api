package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, String> {

    @Query("select concat(e.name, ' (', e.id,  ')') from Exchange e order by e.name")
    List<String> findAllExchangeNames();

    Exchange save(Exchange exchange);

    Optional<Exchange> findById(String id);

    void deleteById(String id);

    @Query("select s from Exchange e join e.stocks s where e.id=?1")
    List<Stock> getAllStocksByExchangeId(String id);

    @Query("select s from Exchange e join e.stocks s where e.id=?1 and s.id=?2")
    Stock getStockByIdAndExchangeId(String exchangeId, String stockId);
}

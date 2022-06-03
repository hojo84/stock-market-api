package com.codecool.stockmarketapi.repository;

import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.entity.TradingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {

    List<Exchange> findAll();

    Exchange save(Exchange exchange);

    Optional<Exchange> findById(Long id);

    void deleteById(Long id);

    Exchange findByMarketIdentifierCode(String marketIdentifierCode);

    @Query("select i from Exchange e join e.indices i where e.marketIdentifierCode=?1")
    List<Index> getAllIndicesByExchangeCode(String marketIdentifierCode);

    @Query("select s from Exchange e join e.stocks s where e.marketIdentifierCode=?1")
    List<Stock> getAllStocksByExchangeCode(String marketIdentifierCode);

    @Query("select s from Exchange e join e.stocks s where e.marketIdentifierCode=?1 and s.tickerSymbol=?2")
    Stock getStockByTickerOnGivenExchange(String marketIdentifierCode, String stockTicker);

    @Query("select t from Exchange e join e.tradingData t join e.stocks s " +
            "where e.marketIdentifierCode=?1 and s.tickerSymbol=?2 and t.tradingDay=?3")
    TradingData getTradingDataByStockTickerOnGivenExchangeAndDate(String marketIdentifierCode, String stockTicker, LocalDate tradingDay);
}

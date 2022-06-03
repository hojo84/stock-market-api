package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.entity.TradingData;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping
    public List<Exchange> listAll() {
        return exchangeService.listAll();
    }

    @PostMapping
    public Exchange save(@RequestBody Exchange exchange) {
        return exchangeService.save(exchange);
    }

    @PutMapping("/{id}")
    public Exchange update(@PathVariable("id") Long id, @RequestBody Exchange exchange) {
        exchange.setId(id);
        return exchangeService.save(exchange);
    }

    @GetMapping("/{id}")
    public Optional<Exchange> findById(@PathVariable("id") Long id) {
        return exchangeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        exchangeService.deleteById(id);
    }

    @GetMapping("/mic/{mic}")
    public Exchange getExchangeByMarketIdentifierCode(@PathVariable("mic") String marketIdentifierCode) {
        return exchangeService.getExchangeByMarketIdentifierCode(marketIdentifierCode);
    }

    @GetMapping("/{mic}/indices")
    public List<Index> getAllIndicesByExchangeCode(@PathVariable("mic") String marketIdentifierCode) {
        return exchangeService.getAllIndicesByExchangeCode(marketIdentifierCode);
    }

    @GetMapping("/{mic}/tickers")
    public List<Stock> getAllStocksByExchangeCode(@PathVariable("mic") String marketIdentifierCode) {
        return exchangeService.getAllStocksByExchangeCode(marketIdentifierCode);
    }

    @GetMapping("/{mic}/tickers/{stock_ticker}")
    public Stock getStockByTickerOnGivenExchange(@PathVariable("mic") String marketIdentifierCode,
                                                 @PathVariable("stock_ticker") String stockTicker) {
        return exchangeService.getStockByTickerOnGivenExchange(marketIdentifierCode, stockTicker);
    }

    @GetMapping("/{mic}/tickers/{stock_ticker}/trading/{date}")
    public TradingData getTradingDataByStockTickerOnGivenExchangeAndDate(@PathVariable("mic") String marketIdentifierCode,
                                                                         @PathVariable("stock_ticker") String stockTicker,
                                                                         @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate tradingDay) {
        return exchangeService.getTradingDataByStockTickerOnGivenExchangeAndDate(marketIdentifierCode, stockTicker, tradingDay);
    }
}

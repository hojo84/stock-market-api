package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.Stock;
import com.codecool.stockmarketapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<Stock> listAll() {
        return stockService.listAll();
    }

    @PostMapping
    public Long save(@RequestBody Stock stock) {
        return stockService.save(stock);
    }

    @PutMapping("/{id}")
    public Long save(@PathVariable("id") Long id, @RequestBody Stock stock) {
        stock.setId(id);
        return stockService.save(stock);
    }

    @GetMapping("/{id}")
    public Stock findById(@PathVariable("id") Long id) {
        return stockService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        stockService.deleteById(id);
    }

    @GetMapping("/tickers/{ticker}")
    public Stock getStockByTicker(@PathVariable("ticker") String ticker) {
        return stockService.getStockByTicker(ticker);
    }

    @GetMapping("/{ticker}/indices")
    public List<Index> getAllIndicesIncludingGivenStockByTicker(@PathVariable("ticker") String ticker) {
        return stockService.getAllIndicesContainingGivenStockByTicker(ticker);
    }
}
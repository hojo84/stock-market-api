package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateStockDTO;
import com.codecool.stockmarketapi.dto.UpdateStockDTO;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<String> listAll() {
        return stockService.listAll();
    }

    @PostMapping
    public Stock save(@RequestBody CreateStockDTO createStockDTO) {
        return stockService.save(createStockDTO);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable("id") String id, @RequestBody UpdateStockDTO updateStockDTO) {
        updateStockDTO.setId(id);
        return stockService.update(updateStockDTO);
    }

    @GetMapping("/{id}")
    public Optional<Stock> findById(@PathVariable("id") String id) {
        return stockService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        stockService.deleteById(id);
    }

    @PostMapping("/{stockId}/listings/{exchangeId}")
    public void addStockToExchangeById(@PathVariable("stockId") String stockId,
                                       @PathVariable("exchangeId") String exchangeId) {
        stockService.addStockToExchangeById(stockId, exchangeId);
    }

    @DeleteMapping("/{stockId}/listings/{exchangeId}")
    public void removeStockFromExchangeById(@PathVariable("stockId") String stockId,
                                            @PathVariable("exchangeId") String exchangeId) {
        stockService.removeStockFromExchangeById(stockId, exchangeId);
    }
}

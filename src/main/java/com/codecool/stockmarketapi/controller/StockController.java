package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.StockDTO;
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
    public Stock save(@RequestBody StockDTO stockDTO) {
        return stockService.save(stockDTO);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable("id") String id, @RequestBody StockDTO stockDTO) {
        stockDTO.setId(id);
        return stockService.save(stockDTO);
    }

    @GetMapping("/{id}")
    public Optional<Stock> findById(@PathVariable("id") String id) {
        return stockService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        stockService.deleteById(id);
    }
}

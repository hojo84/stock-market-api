package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.entity.Index;
import com.codecool.stockmarketapi.entity.IndexComponent;
import com.codecool.stockmarketapi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/indices")
public class IndexController {

    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping
    public List<Index> listAll() {
        return indexService.listAll();
    }

    @PostMapping
    public Index save(@RequestBody Index index) {
        return indexService.save(index);
    }

    @PutMapping("/{id}")
    public Index update(@PathVariable("id") Long id, @RequestBody Index index) {
        index.setId(id);
        return indexService.save(index);
    }

    @GetMapping("/{id}")
    public Optional<Index> findById(@PathVariable("id") Long id) {
        return indexService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        indexService.deleteById(id);
    }

    @GetMapping("/symbols/{symbol}")
    public Index getIndexBySymbol(@PathVariable("symbol") String symbol) {
        return indexService.getIndexBySymbol(symbol);
    }

    @GetMapping("/{symbol}/components")
    public List<IndexComponent> getAllComponentsByIndexSymbol(@PathVariable("symbol") String symbol) {
        return indexService.getAllComponentsByIndexSymbol(symbol);
    }

    @PostMapping("/components")
    public IndexComponent addStockToIndex(@RequestBody IndexComponent indexComponent) {
        return indexService.addStockToIndex(indexComponent);
    }

    @DeleteMapping("/{symbol}/components/{stock_ticker}")
    public void removeStockFromIndex(@PathVariable("stock_ticker") String stockTicker, @PathVariable("symbol") String indexSymbol) {
        indexService.removeStockFromIndex(stockTicker, indexSymbol);
    }
}

package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.model.Index;
import com.codecool.stockmarketapi.model.IndexComponent;
import com.codecool.stockmarketapi.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Long save(@RequestBody Index index) {
        return indexService.save(index);
    }

    @PutMapping("/{id}")
    public Long save(@PathVariable("id") Long id, @RequestBody Index index) {
        index.setId(id);
        return indexService.save(index);
    }

    @GetMapping("/{id}")
    public Index findById(@PathVariable("id") Long id) {
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
    public List<IndexComponent> getAllComponentsByIndexId(@PathVariable("symbol") String symbol) {
        return indexService.getAllComponentsByIndexId(symbol);
    }

    @PostMapping("/components")
    public void addStockToIndex(@RequestBody IndexComponent indexComponent) {
        indexService.addStockToIndex(indexComponent);
    }

    @DeleteMapping("/{symbol}/components/{stock_ticker}")
    public void removeStockFromIndex(@PathVariable("stock_ticker") String stockTicker, @PathVariable("symbol") String indexSymbol) {
        indexService.removeStockFromIndex(stockTicker, indexSymbol);
    }
}

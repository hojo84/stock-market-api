package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.entity.Stock;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Exchange save(@RequestBody ExchangeDTO exchangeDTO) {
        return exchangeService.save(exchangeDTO);
    }

    @PutMapping("/{id}")
    public Exchange update(@PathVariable("id") String id, @RequestBody ExchangeDTO exchangeDTO) {
        exchangeDTO.setId(id);
        return exchangeService.save(exchangeDTO);
    }

    @GetMapping("/{id}")
    public Optional<Exchange> findById(@PathVariable("id") String id) {
        return exchangeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        exchangeService.deleteById(id);
    }

    @GetMapping("/{id}/stocks")
    public List<Stock> getAllStocksByExchangeId(@PathVariable("id") String id) {
        return exchangeService.getAllStocksByExchangeId(id);
    }

    @GetMapping("/{exchangeId}/stocks/{stockId}")
    public Stock getStockByIdAndExchangeId(@PathVariable("exchangeId") String exchangeId,
                                           @PathVariable("stockId") String stockId) {
        return exchangeService.getStockByIdAndExchangeId(exchangeId, stockId);
    }
}

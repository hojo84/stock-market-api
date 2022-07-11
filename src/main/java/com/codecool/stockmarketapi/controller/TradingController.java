package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.service.TradingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trading")
public class TradingController {

    private final TradingService tradingService;

    public TradingController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TradeDTO save(@RequestBody CreateTradeDTO createTradeDTO) {
        return tradingService.save(createTradeDTO);
    }

    @GetMapping
    public List<TradeDTO> listAll(@RequestParam(name = "ticker", required = false) Optional<String> listingId) {
        return tradingService.listAll(listingId);
    }

    @GetMapping("/range/{from}/{to}")
    public List<TradeDTO> listBetweenDateRange(@PathVariable("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
                                               @PathVariable("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
        return tradingService.listBetweenDateRange(from, to);
    }
}
package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.service.TradingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
package com.codecool.stockmarketapi.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TradingData {

    private Long id;
    private Long exchangeId;
    private Long stockId;
    private LocalDate tradingDay;
    private float priceOpen;
    private float priceHigh;
    private float priceLow;
    private float priceClose;
    private int volume;
}

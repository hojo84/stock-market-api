package com.codecool.stockmarketapi.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Trading {

    private Long id;
    private String exchangeId;
    private String stockId;
    private LocalDate tradingDay;
    private float priceOpen;
    private float priceHigh;
    private float priceLow;
    private float priceClose;
    private int volume;
}

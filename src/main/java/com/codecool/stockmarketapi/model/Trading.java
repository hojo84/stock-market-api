package com.codecool.stockmarketapi.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Trading {

    private Long id;
    private String exchangeId;
    private String stockId;
    private LocalDate tradingDay;
    private int priceOpen;
    private int priceHigh;
    private int priceLow;
    private int priceClose;
    private int volume;
}

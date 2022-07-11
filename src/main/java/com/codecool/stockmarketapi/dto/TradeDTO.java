package com.codecool.stockmarketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TradeDTO {

    private Long id;

    private String ticker;

    private LocalDate tradingDay;

    private float priceOpen;

    private float priceHigh;

    private float priceLow;

    private float priceClose;

    private int volume;
}

package com.codecool.stockmarketapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTradeDTO {

    private String listingId;

    private LocalDate tradingDay;

    private float priceOpen;

    private float priceHigh;

    private float priceLow;

    private float priceClose;

    private int volume;
}

package com.codecool.stockmarketapi.model;

import lombok.Data;

@Data
public class Stock {

    private Long id;
    private String tickerSymbol;
    private String companyName;
    private String sector;
    private String industry;
    private String equityType;
}
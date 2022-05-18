package com.codecool.stockmarketapi.model;

import lombok.Data;

@Data
public class Stock {

    private String id;
    private String companyName;
    private String sector;
    private String industry;
    private String equityType;
}
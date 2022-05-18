package com.codecool.stockmarketapi.model;

import lombok.Data;

@Data
public class Index {

    private Long id;
    private String symbol;
    private String name;
    private String exchangeId;
    private int constituents;
}

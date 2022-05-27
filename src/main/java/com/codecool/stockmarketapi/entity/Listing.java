package com.codecool.stockmarketapi.entity;

import lombok.Data;

@Data
public class Listing {

    private Long id;
    private Long exchangeId;
    private Long stockId;
}

package com.codecool.stockmarketapi.entity;

import lombok.Data;

@Data
public class Exchange {

    private Long id;
    private String marketIdentifierCode;
    private String name;
    private Long countryId;
    private String location;
    private String currency;
    private String website;
}

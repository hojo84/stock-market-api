package com.codecool.stockmarketapi.model;

import lombok.Data;

@Data
public class Exchange {

    private String id;
    private String name;
    private Long countryId;
    private String location;
    private String currency;
    private String website;
}

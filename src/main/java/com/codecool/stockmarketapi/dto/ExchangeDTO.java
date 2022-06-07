package com.codecool.stockmarketapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeDTO {

    private String id;
    private String name;
    private String location;
    private String currency;
    private String website;
}
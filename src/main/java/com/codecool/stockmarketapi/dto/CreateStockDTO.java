package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateStockDTO {

    private String id;
    private String companyName;
    private String sector;
    private EquityType equityType;
    private Set<String> exchangeIds;
}

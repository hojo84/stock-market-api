package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockDTO {

    private String id;
    private String companyName;
    private String sector;
    private EquityType equityType;
}

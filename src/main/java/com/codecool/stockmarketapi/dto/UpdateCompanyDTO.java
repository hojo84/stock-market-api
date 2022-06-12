package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCompanyDTO {

    private String id;
    private String name;
    private String sector;
    private EquityType equityType;
}

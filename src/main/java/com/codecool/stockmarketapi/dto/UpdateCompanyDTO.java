package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCompanyDTO {

    @NotBlank(message = "Company id must not be null or blank")
    private String id;
    @NotBlank(message = "Company name must not be null or blank")
    private String name;
    @NotBlank(message = "Sector must not be null or blank")
    private String sector;
    @NotNull(message = "EquityType must not be null")
    private EquityType equityType;
}

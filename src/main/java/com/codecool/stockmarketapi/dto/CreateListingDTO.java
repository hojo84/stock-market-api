package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingDTO {

    private String id;

    private String exchangeId;

    private String companyId;

    private EquityType equityType;

    private LocalDate ipo;
}
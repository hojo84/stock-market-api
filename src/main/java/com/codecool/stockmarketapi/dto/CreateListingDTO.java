package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateListingDTO {

    @Schema(description = "unique identification for listing", example = "AAPLB")
    private String id;

    @Schema(description = "unique identification for exchange", example = "NYSE")
    private String exchangeId;

    @Schema(description = "unique identification for company", example = "AAPL")
    private String companyId;

    @Schema(description = "equity types are different forms of shares or ownership available in a company", example = "COMMON_STOCK")
    private EquityType equityType;

    @Schema(description = "date of Initial Public Offering", example = "2022-03-05")
    private LocalDate ipo;
}
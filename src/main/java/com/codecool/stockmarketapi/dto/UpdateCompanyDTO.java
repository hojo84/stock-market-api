package com.codecool.stockmarketapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCompanyDTO {

    @Schema(description = "unique identification", example = "VZ")
    @NotBlank(message = "Company id must not be null or blank")
    private String id;

    @Schema(description = "name of the company", example = "Verizon Communications Inc.")
    @NotBlank(message = "Company name must not be null or blank")
    private String name;

    @Schema(description = "sector of the company", example = "Communications")
    @NotBlank(message = "Sector must not be null or blank")
    private String sector;

    @Schema(description = "industry of the company", example = "Telecom Services")
    @NotNull(message = "Industry must not be null")
    private String industry;
}

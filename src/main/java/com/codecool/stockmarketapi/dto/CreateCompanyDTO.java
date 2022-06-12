package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class CreateCompanyDTO {

    @NotBlank(message = "Company id must not be null or blank")
    private String id;
    @NotBlank(message = "Company name must not be null or blank")
    private String name;
    @NotBlank(message = "Sector must not be null or blank")
    private String sector;
    @NotNull(message = "EquityType must not be null")
    private EquityType equityType;
    @NotNull(message = "List of exchanges must not be null")
    @Size(min = 1, message = "At least one exchange id must be provided")
    private Set<String> exchangeIds;
}

package com.codecool.stockmarketapi.dto;

import com.codecool.stockmarketapi.entity.EquityType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CreateCompanyDTO {

    @Schema(description = "unique identification", example = "VZ")
    @NotBlank(message = "Company id must not be null or blank")
    private String id;

    @Schema(description = "name of the company", example = "Verizon Communications Inc.")
    @NotBlank(message = "Company name must not be null or blank")
    private String name;

    @Schema(description = "sector of the company", example = "Communications")
    @NotBlank(message = "Sector must not be null or blank")
    private String sector;

    @Schema(description = "types of equity are different forms of shares or ownership available in a company", example = "COMMON_STOCK")
    @NotNull(message = "EquityType must not be null")
    private EquityType equityType;

    @Schema(description = "list of exchange ids where company is traded", example = """
            [
                    "SSE",
                    "NYSE"
                ]
            """)
    @NotNull(message = "List of exchanges must not be null")
    @Size(min = 1, message = "At least one exchange id must be provided")
    private Set<String> exchangeIds;
}

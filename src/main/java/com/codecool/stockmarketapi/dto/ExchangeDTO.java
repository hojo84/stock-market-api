package com.codecool.stockmarketapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeDTO {

    @Schema(description = "unique identification", example = "VSE")
    @NotBlank(message = "Exchange id must not be null or blank")
    private String id;

    @Schema(description = "name of the exchange", example = "Vienna Stock Exchange")
    @NotBlank(message = "Exchange name must not be null or blank")
    private String name;

    @Schema(description = "location of the exchange", example = "Vienna")
    @NotBlank(message = "Exchange location must not be null or blank")
    private String location;

    @Schema(description = "currency of the exchange", example = "EUR")
    @NotBlank(message = "Exchange currency must not be null or blank")
    @Length(min = 3, max = 3, message = "Exchange currency must be a three-letter alphabetic code")
    private String currency;

    @Schema(description = "official website of the exchange", example = "www.wienerborse.at")
    private String website;
}
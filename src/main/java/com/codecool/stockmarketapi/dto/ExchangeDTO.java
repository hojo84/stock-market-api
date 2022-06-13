package com.codecool.stockmarketapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ExchangeDTO {

    @NotBlank(message = "Exchange id must not be null or blank")
    private String id;
    @NotBlank(message = "Exchange name must not be null or blank")
    private String name;
    @NotBlank(message = "Exchange location must not be null or blank")
    private String location;
    @NotBlank(message = "Exchange currency must not be null or blank")
    @Length(min = 3, max = 3, message = "Exchange currency must be a three-letter alphabetic code")
    private String currency;
    private String website;
}
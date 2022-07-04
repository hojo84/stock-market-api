package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCountryDTO;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public Country save(@RequestBody CreateCountryDTO createCountryDTO) {
        return countryService.save(createCountryDTO);
    }
}

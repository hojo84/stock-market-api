package com.codecool.stockmarketapi.controller;

import com.codecool.stockmarketapi.dto.CreateCountryDTO;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping
    public Country save(@RequestBody CreateCountryDTO createCountryDTO) {
        return countryService.save(createCountryDTO);
    }

    @PutMapping("/{countryId}/addExchange/{exchangeId}")
    public void addExchangeToCountryById(@PathVariable("countryId") Long countryId,
                                         @PathVariable("exchangeId") String exchangeId) {
        countryService.addExchangeToCountryById(countryId, exchangeId);
    }
}

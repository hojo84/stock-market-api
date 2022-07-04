package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.dto.CreateCountryDTO;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country save(CreateCountryDTO createCountryDTO) {
        Country country = new Country(createCountryDTO.getName());
        return countryRepository.save(country);
    }
}

package com.codecool.stockmarketapi.service;

import com.codecool.stockmarketapi.customexception.ExchangeNotFoundException;
import com.codecool.stockmarketapi.dto.CreateCountryDTO;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.repository.CountryRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    private final ExchangeRepository exchangeRepository;

    public Country save(CreateCountryDTO createCountryDTO) {
        Country country = new Country(createCountryDTO.getName());
        return countryRepository.save(country);
    }

    public void addExchangeToCountryById(Long countryId, String exchangeId) {
        Country country = countryRepository.findById(countryId)
                .orElseThrow(() -> new IllegalArgumentException("Country does not exists: " + countryId));
        exchangeRepository.findById(exchangeId)
                .orElseThrow(() -> new ExchangeNotFoundException(exchangeId));
        exchangeRepository.addExchangeToCountryById(country, exchangeId);
    }
}

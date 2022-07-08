package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.CreateCountryDTO;
import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.repository.CountryRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.repository.TradingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    TradingRepository tradingRepository;

    private String url;
    private CreateCountryDTO createCountryDTO = new CreateCountryDTO("United States");
    private ExchangeDTO exchangeDTO = new ExchangeDTO(
            "XNAS",
            "Nasdaq Stock Market",
            "New York",
            "USD",
            "https://www.nasdaq.com");

    @BeforeEach
    void setUp() {
        resetRepositories();
        url = "/countries";
    }

    private void resetRepositories() {
        tradingRepository.deleteAll();
        exchangeRepository.deleteAll();
        countryRepository.deleteAll();
    }

    @Test
    void testReturnExchangeWithCountryIfNewExchangeAndCountryIsPostedAndExchangeIsAddedToCountry() {
        testRestTemplate.postForObject("/exchanges",
                exchangeDTO,
                Exchange.class);

        final Country country = testRestTemplate.postForObject(url,
                createCountryDTO,
                Country.class);

        testRestTemplate.put(url + "/" + country.getId() + "/addExchange/" + exchangeDTO.getId(),
                null);

        final Exchange updatedExchange = testRestTemplate.getForObject("/exchanges" + "/" + exchangeDTO.getId(), Exchange.class);

        assertEquals("United States", updatedExchange.getCountry().getName());
    }
}
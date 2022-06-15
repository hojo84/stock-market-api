package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeRestTemplateIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void testListExchanges() {
        exchangeRepository.deleteAll();
        companyRepository.deleteAll();

        ExchangeDTO exchangeDTO = testRestTemplate.postForObject("/exchanges",
                new ExchangeDTO("XNAS", "Nasdaq Stock Market", "New York", "USD", "www.nasdaq.com"),
                ExchangeDTO.class);

        assertEquals("Nasdaq Stock Market", exchangeDTO.getName());

        testRestTemplate.postForObject("/exchanges",
                new ExchangeDTO("VSE", "Vienna Stock Exchange", "Vienna", "EUR", "www.wienerborse.at"),
                ExchangeDTO.class);

        final List<String> exchanges = testRestTemplate.exchange("/exchanges",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<String>>() {
                        })
                .getBody();

        assertThat(exchanges).containsExactly("Nasdaq Stock Market (XNAS)", "Vienna Stock Exchange (VSE)");
    }
}

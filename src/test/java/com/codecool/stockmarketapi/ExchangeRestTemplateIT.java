package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
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

    private String url;
    private List<ExchangeDTO> exchangeDTOs;

    @BeforeEach
    void setUp() {
        resetRepositories();
        url = "/exchanges";
        exchangeDTOs = List.of(
                new ExchangeDTO("XNAS", "Nasdaq Stock Market", "New York", "USD", "www.nasdaq.com"),
                new ExchangeDTO("VSE", "Vienna Stock Exchange", "Vienna", "EUR", "www.wienerborse.at")
        );
    }

    private void resetRepositories() {
        exchangeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void testReturnEmptyJsonIfDatabaseIsEmpty() {
        final String result = testRestTemplate.getForObject(url, String.class);
        assertEquals("[]", result);
    }

    @Test
    void testReturnCreatedExchangeIfNewExchangePosted() {
        ExchangeDTO response = postExchange(url, exchangeDTOs.get(0));

        assertEquals(exchangeDTOs.get(0).getId(), response.getId());
        assertEquals(exchangeDTOs.get(0).getName(), response.getName());
    }


    @Test
    void testReturnAllExchangesIfDatabaseHasContent() {
        postExchange(url, exchangeDTOs.get(0));
        postExchange(url, exchangeDTOs.get(1));

        final List<String> exchanges = testRestTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<String>>() {
                        })
                .getBody();

        assertThat(exchanges).containsExactly("Nasdaq Stock Market (XNAS)", "Vienna Stock Exchange (VSE)");
    }

    private ExchangeDTO postExchange(String url, ExchangeDTO exchangeDTO) {
        return testRestTemplate.postForObject(url,
                exchangeDTO,
                ExchangeDTO.class);
    }
}

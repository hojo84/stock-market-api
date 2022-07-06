package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExchangeIntegrationTests {

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
                new ExchangeDTO("XNAS", "Nasdaq Stock Market", "New York", "USD", "https://www.nasdaq.com"),
                new ExchangeDTO("VSE", "Vienna Stock Exchange", "Vienna", "EUR", "https://www.wienerborse.at")
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
        Exchange response = postExchange(url, exchangeDTOs.get(0));

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

    @Test
    void testReturnUpdatedExchangeIfNewExchangeIsPostedAndUpdated() {
        final Exchange origExchange = postExchange(url, exchangeDTOs.get(0));

        ExchangeDTO renamedExchangeDTO = new ExchangeDTO(
                origExchange.getId(),
                "Renamed Nasdaq",
                origExchange.getLocation(),
                origExchange.getCurrency(),
                origExchange.getWebsite()
        );

        testRestTemplate.put(url + "/" + renamedExchangeDTO.getId(), renamedExchangeDTO);

        final Exchange updatedExchange = testRestTemplate.getForObject(url + "/" + renamedExchangeDTO.getId(), Exchange.class);

        assertEquals("Renamed Nasdaq", updatedExchange.getName());
    }

    @Test
    void testReturnOneLessIfNewExchangeIsPostedAndDeleted() {
        final String[] exchangesBeforePost = testRestTemplate.getForObject(url, String[].class);
        assertThat(exchangesBeforePost.length).isEqualTo(0);

        postExchange(url, exchangeDTOs.get(0));
        postExchange(url, exchangeDTOs.get(1));

        testRestTemplate.delete(url + "/" + exchangeDTOs.get(0).getId());

        final List<String> exchangesAfterTwoPostsAndOneDelete = testRestTemplate.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<String>>() {
                        })
                .getBody();

        assertThat(exchangesAfterTwoPostsAndOneDelete).hasSize(1);
        assertThat(exchangesAfterTwoPostsAndOneDelete).containsExactly("Vienna Stock Exchange (VSE)");
    }

    @Test
    void testReturnCompanyByIdAndExchangeIdIfExchangeAndCompanyPostedToDatabase() {
        postExchange(url, exchangeDTOs.get(0));
        String exchangeId = exchangeDTOs.get(0).getId();

        CreateCompanyDTO newCompany = new CreateCompanyDTO("NVDA", "Nvidia", "IT", EquityType.COMMON_STOCK,
                Set.of(exchangeId));

        testRestTemplate.postForObject("/companies", newCompany, Company.class);


        String companyId = newCompany.getId();

        final Company response = testRestTemplate.getForObject(url + "/{exchangeId}/companies/{companyId}", Company.class, exchangeId, companyId);

        assertThat(response.getName()).isEqualTo("Nvidia");
        assertThat(response.getSector()).isEqualTo("IT");
    }

    private Exchange postExchange(String url, ExchangeDTO exchangeDTO) {
        HttpEntity<ExchangeDTO> httpEntity = createHttpEntity(exchangeDTO);
        final ResponseEntity<Exchange> postResponse = testRestTemplate.postForEntity(url, httpEntity, Exchange.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        return postResponse.getBody();
    }

    private HttpEntity<ExchangeDTO> createHttpEntity(ExchangeDTO exchangeDTO) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExchangeDTO> httpEntity = new HttpEntity<>(exchangeDTO, httpHeaders);
        return httpEntity;
    }
}

package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.dto.UpdateCompanyDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyIntegrationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    CompanyRepository companyRepository;

    private String url;
    private List<CreateCompanyDTO> createCompanyDTOSs;

    @BeforeEach
    void setUp() {
        resetRepositories();
        url = "/companies";
        createCompanyDTOSs = List.of(
                new CreateCompanyDTO("NVDA", "Nvidia", "IT", "Semiconductors", Set.of("XNAS"))
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
    void testReturnUpdatedCompanyIfNewCompanyIsPostedAndUpdated() {
        final ExchangeDTO newExchange = new ExchangeDTO("XNAS", "Nasdaq Stock Market", "New York", "USD", "https://www.nasdaq.com");
        testRestTemplate.postForObject("/exchanges",
                newExchange,
                ExchangeDTO.class);

        final Company origCompany = testRestTemplate.postForObject(url, createCompanyDTOSs.get(0), Company.class);

        UpdateCompanyDTO updateCompanyDTO = new UpdateCompanyDTO(
                origCompany.getId(),
                "Renamed Nvidia",
                origCompany.getSector(),
                origCompany.getIndustry()
        );

        testRestTemplate.put(url + "/" + updateCompanyDTO.getId(), updateCompanyDTO);

        final Company updatedCompany = testRestTemplate.getForObject(url + "/" + updateCompanyDTO.getId(), Company.class);

        assertEquals("Renamed Nvidia", updatedCompany.getName());
    }
}

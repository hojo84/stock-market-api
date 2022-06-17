package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.repository.CompanyRepository;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompanyRestTemplateIT {

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
                new CreateCompanyDTO("NVDA", "Nvidia", "IT", EquityType.COMMON_STOCK, Set.of("XNAS"))
        );
    }

    private void resetRepositories() {
        exchangeRepository.deleteAll();
        companyRepository.deleteAll();
    }
}

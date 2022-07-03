package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ExchangeServiceUnitTests {

    @MockBean
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeService exchangeService;

    private final Country hungary = new Country(1L, "Hungary");

    @Test
    void testReturnAllExchanges() {

        List<String> expectedExchanges = List.of(
                "London Stock Exchange (LSE)",
                "Nasdaq Stock Market (XNAS)",
                "New York Stock Exchange (NYSE)"
        );
        when(exchangeRepository.findAllExchangeNames()).thenReturn(expectedExchanges);

        final List<String> actualExchanges = exchangeService.listAll();

        assertThat(actualExchanges).hasSize(3);
        assertThat(actualExchanges.get(0)).isEqualTo("London Stock Exchange (LSE)");
        assertThat(actualExchanges.get(2)).isEqualTo("New York Stock Exchange (NYSE)");
        assertThat(actualExchanges).doesNotContain("Budapest Stock Exchange");
    }

    @Test
    void testReturnAllCompaniesByExchangeId() {
        List<Company> expectedCompanies = List.of(
                new Company("MOL", "MOL Nyrt", "Energy", EquityType.COMMON_STOCK, Collections.emptySet()),
                new Company("OTP", "OTP Nyrt", "Financials", EquityType.COMMON_STOCK, Collections.emptySet())
        );
        Exchange exchange = new Exchange("BUD",
                "Budapest Stock Exchange",
                "Budapest",
                "HUF",
                "bet.hu",
                hungary,
                Collections.emptySet());
        String exchangeId = "BUD";

        when(exchangeRepository.getAllCompaniesByExchangeId(exchangeId)).thenReturn(expectedCompanies);
        when(exchangeRepository.findById(exchangeId)).thenReturn(Optional.of(exchange));

        final List<Company> actualCompanies = exchangeService.getAllCompaniesByExchangeId(exchangeId);

        assertThat(actualCompanies).hasSize(2);
        assertThat(actualCompanies).extracting("id").contains("MOL", "OTP");
        assertThat(actualCompanies).extracting("name").contains("MOL Nyrt", "OTP Nyrt");
        assertThat(actualCompanies).extracting("sector").contains("Energy", "Financials");
    }
}
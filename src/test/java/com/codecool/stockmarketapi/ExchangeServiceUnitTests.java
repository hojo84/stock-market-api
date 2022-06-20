package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.repository.ExchangeRepository;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ExchangeServiceUnitTests {

    @MockBean
    private ExchangeRepository exchangeRepository;

    @Autowired
    private ExchangeService exchangeService;

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
}
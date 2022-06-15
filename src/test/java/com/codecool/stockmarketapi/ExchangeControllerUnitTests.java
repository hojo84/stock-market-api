package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.ExchangeController;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeController.class)
public class ExchangeControllerUnitTests {

    @MockBean
    private ExchangeService exchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllExchanges() throws Exception {
        List<String> expectedExchanges = List.of(
                "London Stock Exchange (LSE)",
                "Nasdaq Stock Market (XNAS)",
                "New York Stock Exchange (NYSE)"
        );

        when(exchangeService.listAll()).thenReturn(expectedExchanges);

        mockMvc.perform(get("/exchanges"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(3)))
                .andExpect(jsonPath("$[0]", equalTo("London Stock Exchange (LSE)")));
    }

    @Test
    public void testFindExchangeById() throws Exception {
        Exchange expected = new Exchange("XNAS", "Nasdaq", "New York", "USD", "www.nasdaq.com",
                Collections.emptySet());

        when(exchangeService.findById(expected.getId())).thenReturn(expected);

        mockMvc.perform(get("/exchanges/{id}", expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Nasdaq")))
                .andExpect(jsonPath("$.location", equalTo("New York")))
                .andExpect(jsonPath("$.currency", equalTo("USD")))
                .andExpect(jsonPath("$.website", equalTo("www.nasdaq.com")));
    }
}

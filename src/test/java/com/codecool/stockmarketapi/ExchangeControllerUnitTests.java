package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.ExchangeController;
import com.codecool.stockmarketapi.dto.ExchangeDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.Country;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.entity.Exchange;
import com.codecool.stockmarketapi.service.ExchangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeController.class)
public class ExchangeControllerUnitTests {

    @MockBean
    private ExchangeService exchangeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final Country unitedStates = new Country(1L, "United States");

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
        Exchange expected = new Exchange("XNAS", "Nasdaq", "New York", "USD", "https://www.nasdaq.com",
                unitedStates, Collections.emptySet());

        when(exchangeService.findById(expected.getId())).thenReturn(expected);

        mockMvc.perform(get("/exchanges/{id}", expected.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Nasdaq")))
                .andExpect(jsonPath("$.location", equalTo("New York")))
                .andExpect(jsonPath("$.currency", equalTo("USD")))
                .andExpect(jsonPath("$.website", equalTo("https://www.nasdaq.com")));
    }

    @Test
    public void testSaveExchange() throws Exception {
        Exchange expected = new Exchange("XNAS", "Nasdaq", "New York", "USD", "https://www.nasdaq.com",
                unitedStates, Collections.emptySet());
        ExchangeDTO exchangeDTO = new ExchangeDTO("XNAS", "Nasdaq", "New York", "USD", "https://www.nasdaq.com");

        when(exchangeService.save(any())).thenReturn(expected);

        mockMvc.perform(post("/exchanges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(exchangeDTO)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("XNAS")));
    }

    @Test
    public void testDeleteExchangeById() throws Exception {
        String id = "XNAS";

        doNothing().when(exchangeService).deleteById(id);

        mockMvc.perform(delete("/exchanges/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllCompaniesByExchangeId() throws Exception {
        Exchange nasdaq = new Exchange("XNAS", "Nasdaq", "New York", "USD", "https://www.nasdaq.com",
                unitedStates, new HashSet<>());
        Company nvidia = new Company("NVDA", "Nvidia", "Information Technology", EquityType.COMMON_STOCK,
                new HashSet<>());
        Company apple = new Company("AAPL", "Apple", "Information Technology", EquityType.COMMON_STOCK,
                new HashSet<>());

        nvidia.getExchanges().add(nasdaq);
        apple.getExchanges().add(nasdaq);

        List<Company> expectedCompanies = List.of(nvidia, apple);
        String exchangeId = "XNAS";

        when(exchangeService.getAllCompaniesByExchangeId(exchangeId)).thenReturn(expectedCompanies);

        mockMvc.perform(get("/exchanges/{id}/companies", exchangeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(2)))
                .andExpect(jsonPath("$[0].id", equalTo("NVDA")))
                .andExpect(jsonPath("$[0].exchanges[0].id", equalTo("XNAS")))
                .andExpect(jsonPath("$[1].id", equalTo("AAPL")))
                .andExpect(jsonPath("$[1].exchanges[0].id", equalTo("XNAS")));
    }
}

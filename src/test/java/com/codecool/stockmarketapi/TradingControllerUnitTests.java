package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.TradingController;
import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.service.TradingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TradingController.class)
public class TradingControllerUnitTests {

    private final String url = "/trading";

    @MockBean
    private TradingService tradingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveTrading() throws Exception {
        CreateTradeDTO newTradingData = new CreateTradeDTO(
                "NVDA",
                LocalDate.of(2022, 7, 8),
                154.30f,
                160.37f,
                153.89f,
                158.38f,
                46763500
        );

        TradeDTO expected = new TradeDTO(
                1L,
                "NVDA",
                LocalDate.of(2022, 7, 8),
                154.30f,
                160.37f,
                153.89f,
                158.38f,
                46763500
        );

        when(tradingService.save(any(CreateTradeDTO.class))).thenReturn(expected);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTradingData)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticker", equalTo("NVDA")))
                .andExpect(jsonPath("$.priceClose", equalTo(158.38)));
    }
}

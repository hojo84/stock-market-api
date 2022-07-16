package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.TradingController;
import com.codecool.stockmarketapi.customexception.TradingNotFoundException;
import com.codecool.stockmarketapi.dto.CreateTradeDTO;
import com.codecool.stockmarketapi.dto.TradeDTO;
import com.codecool.stockmarketapi.service.TradingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private List<CreateTradeDTO> listOfTradingDataToBeSaved;
    private List<TradeDTO> expectedTradingData;

    @BeforeEach
    void setUp() {
        listOfTradingDataToBeSaved = List.of(
                new CreateTradeDTO(
                        "NVDA",
                        LocalDate.of(2022, 7, 8),
                        154.30f,
                        160.37f,
                        153.89f,
                        158.38f,
                        46763500
                ),
                new CreateTradeDTO(
                        "NVDA",
                        LocalDate.of(2022, 7, 15),
                        156.59f,
                        157.82f,
                        154.45f,
                        157.62f,
                        38447100
                )
        );
        expectedTradingData = List.of(
                new TradeDTO(
                        1L,
                        "NVDA",
                        LocalDate.of(2022, 7, 8),
                        154.30f,
                        160.37f,
                        153.89f,
                        158.38f,
                        46763500
                ),
                new TradeDTO(
                        2L,
                        "AAPL",
                        LocalDate.of(2022, 7, 15),
                        156.59f,
                        157.82f,
                        154.45f,
                        157.62f,
                        38447100
                )
        );
    }

    @Test
    void testSaveTrading() throws Exception {
        CreateTradeDTO newTradingData = listOfTradingDataToBeSaved.get(0);

        TradeDTO expected = expectedTradingData.get(0);

        when(tradingService.save(any(CreateTradeDTO.class))).thenReturn(expected);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTradingData)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticker", equalTo("NVDA")))
                .andExpect(jsonPath("$.priceClose", equalTo(158.38)));
    }

    @Test
    void testListAll() throws Exception {
        when(tradingService.listAll(Optional.empty())).thenReturn(expectedTradingData);

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(2)))
                .andExpect(jsonPath("$.[0].ticker", equalTo("NVDA")))
                .andExpect(jsonPath("$.[1].ticker", equalTo("AAPL")));
    }

    @Test
    void testListBetweenDateRange() throws Exception {
        LocalDate from = LocalDate.of(2022, 7, 8);
        LocalDate to = LocalDate.of(2022, 7, 15);

        when(tradingService.listBetweenDateRange(from, to)).thenReturn(expectedTradingData);

        String dateFrom = "2022-07-08";
        String dateTo = "2022-07-15";

        mockMvc.perform(get(url + "/range/" + dateFrom + "/" + dateTo))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(2)))
                .andExpect(jsonPath("$.[0].ticker", equalTo("NVDA")))
                .andExpect(jsonPath("$.[1].ticker", equalTo("AAPL")));
    }

    @Test
    void testGetTradeByListingIdAndDate() throws Exception {
        LocalDate tradingDay = LocalDate.of(2022, 7, 15);
        String stockTicker = "AAPL";

        when(tradingService.getTradeByListingIdAndDate(stockTicker, tradingDay)).thenReturn(expectedTradingData.get(1));

        mockMvc.perform(get(url + "/" + stockTicker + "/" + tradingDay))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticker", equalTo("AAPL")))
                .andExpect(jsonPath("$.tradingDay", equalTo("2022-07-15")));
    }

    @Test
    void testGetTradeForPrevTradingDayNotFound() throws Exception {
        String stockTicker = "VZ";

        when(tradingService.getTradeForPrevTradingDay(stockTicker)).thenThrow(new TradingNotFoundException(stockTicker));

        mockMvc.perform(get(url + "/" + stockTicker))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(tradingService, never()).getTradeForPrevTradingDay(anyString());
    }
}

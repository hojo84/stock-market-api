package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.ListingController;
import com.codecool.stockmarketapi.dto.CreateListingDTO;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.service.ListingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ListingController.class)
public class ListingControllerUnitTests {

    @MockBean
    private ListingService listingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddCompanyToExchange() throws Exception {
        final CreateListingDTO createListingDTO = new CreateListingDTO(
                "NVDA",
                "NYSE",
                "NVDA",
                EquityType.COMMON_STOCK,
                LocalDate.now()
        );
        doNothing().when(listingService).addCompanyToExchangeById(createListingDTO);
        mockMvc.perform(post("/listings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createListingDTO)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void testRemoveCompanyFromExchange() throws Exception {
        String listingId = "NVDA";
        doNothing().when(listingService).removeCompanyFromExchangeById(listingId);
        mockMvc.perform(delete("/listings/{listingId}", listingId))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
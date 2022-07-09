package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.CompanyController;
import com.codecool.stockmarketapi.dto.CreateCompanyDTO;
import com.codecool.stockmarketapi.dto.CreateListingDTO;
import com.codecool.stockmarketapi.entity.Company;
import com.codecool.stockmarketapi.entity.EquityType;
import com.codecool.stockmarketapi.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CompanyController.class)
public class CompanyControllerUnitTests {

    @MockBean
    private CompanyService companyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindAllCompanies() throws Exception {
        List<String> expectedCompanies = List.of(
                "Apple (AAPL)",
                "MOL Nyrt (MOL)",
                "Nvidia (NVDA)"
        );

        when(companyService.listAll()).thenReturn(expectedCompanies);

        mockMvc.perform(get("/companies"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", equalTo(3)))
                .andExpect(jsonPath("$[0]", equalTo("Apple (AAPL)")))
                .andExpect(jsonPath("$[1]", equalTo("MOL Nyrt (MOL)")));
    }

    @Test
    public void testFindCompanyById() throws Exception {
        Company nvidia = new Company("NVDA", "Nvidia", "Information Technology", "Semiconductors", Collections.emptySet());

        when(companyService.findById("NVDA")).thenReturn(nvidia);

        mockMvc.perform(get("/companies/NVDA"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Nvidia")))
                .andExpect(jsonPath("$.sector", equalTo("Information Technology")))
                .andExpect(jsonPath("$.industry", equalTo("Semiconductors")));
    }

    @Test
    public void testSaveCompany() throws Exception {
        Company nvidia = new Company("NVDA", "Nvidia", "IT", "Semiconductors", Collections.emptySet());
        CreateCompanyDTO newCompany = new CreateCompanyDTO("NVDA", "Nvidia", "IT", "Semiconductors",
                Set.of(new CreateListingDTO("NVDA", "NYSE", "NVDA", EquityType.COMMON_STOCK, LocalDate.now())));

        when(companyService.save(any())).thenReturn(nvidia);

        mockMvc.perform(post("/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCompany)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo("NVDA")));
    }

    @Test
    public void testDeleteCompany() throws Exception {
        String id = "NVDA";
        doNothing().when(companyService).deleteById(id);
        mockMvc.perform(delete("/companies/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}

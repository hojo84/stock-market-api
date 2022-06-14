package com.codecool.stockmarketapi;

import com.codecool.stockmarketapi.controller.CompanyController;
import com.codecool.stockmarketapi.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CompanyController.class)
public class CompanyControllerUnitTests {

    @MockBean
    CompanyService companyService;

    @Autowired
    MockMvc mockMvc;

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

}

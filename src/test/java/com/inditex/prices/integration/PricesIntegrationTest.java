package com.inditex.prices.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesIntegrationTest {
    private final String GET_PRICES_INFO_ENDPOINT = "/api/v1/get-prices-info";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Caso 1: Petición a las 10:00 del día 14 debería devolver primera fila")
    void shouldReturnValidPrice() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Caso 2: Petición a las 16:00 del día 14 debería devolver la fila dos")
    void shouldReturnValidPrice2() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Caso 3: Petición a las 21:00 del día 14 debería devolver la fila uno")
    void shouldReturnValidPrice3() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Caso 4: Petición a las 10:00 del día 15 debería devolver la fila tres")
    void shouldReturnValidPrice4() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Caso 5: Petición a las 21:00 del día 16 debería devolver la fila cuatro")
    void shouldReturnValidPrice5() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(38.95));
    }
}

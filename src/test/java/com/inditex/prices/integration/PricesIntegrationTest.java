package com.inditex.prices.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PricesIntegrationTest {
    private final String GET_PRICES_INFO_ENDPOINT = "/api/v1/get-prices-info";
    private final String POST_PRICES = "/api/v1/create-prices";

    @Autowired
    MockMvc mockMvc;

   @Autowired
   ObjectMapper objectMapper;


    @Test
    @DisplayName("Caso 1: Petición a las 10:00 del día 14 debería devolver la tarifa 1")
    void shouldReturnValidPrice() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Caso 2: Petición a las 16:00 del día 14 debería devolver la tarifa 2")
    void shouldReturnValidPrice2() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Caso 3: Petición a las 21:00 del día 14 debería devolver la tarifa 1")
    void shouldReturnValidPrice3() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Caso 4: Petición a las 10:00 del día 15 debería devolver la tarifa 3")
    void shouldReturnValidPrice4() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Caso 5: Petición a las 21:00 del día 16 debería devolver la tarifa 4")
    void shouldReturnValidPrice5() throws Exception {
        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    @DisplayName("Creamos dos precios y recuperamos correctamente el de prioridad mayor")
    void shouldCreatePrices() throws Exception {
        PricesDto dto = PricesDto.builder()
            .productId("35455")
            .brandId(11)
            .startDate(LocalDateTime.of(2020, 6, 16, 21, 0))
            .endDate(LocalDateTime.of(2020, 6, 18, 21, 0))
            .priceList(6)
            .price(11.25)
            .priority(2)
            .currencyCode("EUR")
            .build();

        PricesDto dto2 = PricesDto.builder()
            .productId("35455")
            .brandId(11)
            .startDate(LocalDateTime.of(2020, 6, 16, 21, 0))
            .endDate(LocalDateTime.of(2020, 6, 18, 21, 0))
            .priceList(5)
            .price(22.3)
            .priority(1)
            .currencyCode("EUR")
            .build();

        mockMvc.perform(post(POST_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated());

        mockMvc.perform(post(POST_PRICES)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto2)))
            .andExpect(status().isCreated());

        mockMvc.perform(get(GET_PRICES_INFO_ENDPOINT)
                .param("date", "2020-06-17T21:00:00")
                .param("productId", "35455")
                .param("brandId", "11"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.price").value(11.25))
            .andExpect(jsonPath("$.priceList").value(6));
    }
}

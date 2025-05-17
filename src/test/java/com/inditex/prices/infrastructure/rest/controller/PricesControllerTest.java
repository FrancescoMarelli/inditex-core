package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import com.inditex.prices.infrastructure.rest.exceptions.InvalidCurrencyCodeException;
import com.inditex.prices.infrastructure.rest.mapper.PricesRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class PricesControllerTest {

    @Mock
    private CreatePricesUseCase createPricesUseCase;

    @Mock
    private GetPricesUseCase getPricesUseCase;

    @Mock
    private PricesRestMapper mapper;

    @InjectMocks
    private PricesController controller;

    private PricesDto dto;
    private Prices domain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dto = PricesDto.builder()
            .brandId(1)
            .productId("12345")
            .currencyCode("EUR")
            .build();

        domain = Prices.builder()
            .id(1)
            .brandId(1)
            .productId("12345")
            .currencyCode("EUR")
            .build();
    }

    @Test
    void shouldCreatePrices() {
        when(mapper.toDomain(dto)).thenReturn(domain);
        when(createPricesUseCase.createPrice(domain)).thenReturn(domain);
        when(mapper.toDto(domain)).thenReturn(dto);

        ResponseEntity<PricesDto> response = controller.createPrices(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());

        verify(mapper).toDomain(dto);
        verify(createPricesUseCase).createPrice(domain);
        verify(mapper).toDto(domain);
    }

    @Test
    void shouldGetPricesInfo() {
        LocalDateTime date = LocalDateTime.now();
        Integer productId = 123;
        Integer brandId = 1;

        when(getPricesUseCase.getValidPricesByProductIdAndBrandId(date, productId, brandId))
            .thenReturn(domain);
        when(mapper.toDto(domain)).thenReturn(dto);

        ResponseEntity<PricesDto> response = controller.pricesInformation(date, productId, brandId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dto, response.getBody());

        verify(getPricesUseCase).getValidPricesByProductIdAndBrandId(date, productId, brandId);
        verify(mapper).toDto(domain);
    }
    @Test
    void shouldThrowExceptionForInvalidCurrencyCode() {
        // Given
        dto.setCurrencyCode("XYZ");

        // When And Then
        InvalidCurrencyCodeException exception = assertThrows(
            InvalidCurrencyCodeException.class,
            () -> controller.createPrices(dto)
        );

        assertEquals("Código de moneda inválido: XYZ", exception.getMessage());

        verifyNoInteractions(mapper, createPricesUseCase);
    }


}

package com.inditex.prices.infrastructure.rest.controller;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.CreatePricesUseCase;
import com.inditex.prices.domain.ports.GetPricesUseCase;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import com.inditex.prices.infrastructure.rest.dto.PricesQueryDto;
import com.inditex.prices.infrastructure.rest.mapper.PricesRestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(mapper).toDomain(dto);
        verify(createPricesUseCase).createPrice(domain);
        verify(mapper).toDto(domain);
    }

    @Test
    void shouldGetPricesInfo() {
        PricesQueryDto queryDto = PricesQueryDto.builder().date(LocalDateTime.now()).brandId(1).productId(123).build();

        when(getPricesUseCase.getValidPricesByProductIdAndBrandId(queryDto.getDate(), queryDto.getProductId(), queryDto.getBrandId()))
            .thenReturn(domain);
        when(mapper.toDto(domain)).thenReturn(dto);

        ResponseEntity<PricesDto> response = controller.pricesInformation(queryDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(getPricesUseCase).getValidPricesByProductIdAndBrandId(queryDto.getDate(), queryDto.getProductId(), queryDto.getBrandId());
        verify(mapper).toDto(domain);
    }

}

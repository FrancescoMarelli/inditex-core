package com.inditex.prices.infrastructure.rest.mapper;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PricesRestMapperTest {

    private final PricesRestMapper mapper = new PricesRestMapperImpl();

    @Test
    void shouldMapToDto() {
        Prices domain = Prices.builder()
            .id(1)
            .brandId(2)
            .productId("12345")
            .currencyCode("EUR")
            .build();

        PricesDto dto = mapper.toDto(domain);

        assertEquals(domain.getBrandId(), dto.getBrandId());
        assertEquals(domain.getProductId(), dto.getProductId());
        assertEquals(domain.getCurrencyCode(), dto.getCurrencyCode());
    }

    @Test
    void shouldMapToDomain() {
        PricesDto dto = PricesDto.builder()
            .brandId(20)
            .price(12.22)
            .priceList(2)
            .productId("98765")
            .currencyCode("USD")
            .build();

        Prices domain = mapper.toDomain(dto);

        assertEquals(dto.getBrandId(), domain.getBrandId());
        assertEquals(dto.getProductId(), domain.getProductId());
        assertEquals(dto.getPrice(), domain.getPrice());
        assertEquals(dto.getPriceList(), domain.getPriceList());
        assertEquals(dto.getCurrencyCode(), domain.getCurrencyCode());
    }
}

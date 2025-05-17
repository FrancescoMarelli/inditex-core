package com.inditex.prices.infrastructure.persistence.mapper;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PricesPersistenceMapperTest {

    private PricesPersistenceMapper mapper = new PricesPersistenceMapperImpl();

    @Test
    void shouldMapToEntity() {
        Prices domain = Prices.builder().id(1).brandId(2).productId("3").currencyCode("EUR").build();

        PricesEntity entity = mapper.toEntity(domain);

        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getBrandId(), entity.getBrandId());
        assertEquals(domain.getProductId(), entity.getProductId());
        assertEquals(domain.getCurrencyCode(), entity.getCurr().getCurrencyCode());
    }

    @Test
    void shouldMapToDomain() {
        PricesEntity entity = PricesEntity.builder().id(1).brandId(2).productId("3").curr(Currency.getInstance("USD")).build();

        Prices domain = mapper.toDomain(entity);

        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getBrandId(), domain.getBrandId());
        assertEquals(entity.getProductId(), domain.getProductId());
        assertEquals(entity.getCurr().getCurrencyCode(), domain.getCurrencyCode());
    }

    @Test
    void shouldMapToDomainList() {
        PricesEntity e1 = PricesEntity.builder().id(1).curr(Currency.getInstance("USD")).build();
        PricesEntity e2 = PricesEntity.builder().id(1).curr(Currency.getInstance("EUR")).build();


        List<PricesEntity> entities = List.of(e1, e2);

        List<Prices> result = mapper.toDomainList(entities);

        assertEquals(2, result.size());
        assertEquals("USD", result.get(0).getCurrencyCode());
        assertEquals("EUR", result.get(1).getCurrencyCode());
    }
}

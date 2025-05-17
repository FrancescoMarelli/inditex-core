package com.inditex.prices.infrastructure.persistence;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import com.inditex.prices.infrastructure.persistence.mapper.PricesPersistenceMapper;
import com.inditex.prices.infrastructure.persistence.mapper.PricesPersistenceMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesAdapterTest {
    private PricesAdapter sutAdapter;
    @Mock
    private PricesJpaRepository repository;

    private PricesPersistenceMapper mapper = new PricesPersistenceMapperImpl();

    @BeforeEach
    public void setUp() {
        sutAdapter = new PricesAdapter(repository, mapper);
    }

    @Test
    void shouldCreatePriceSuccessfully() {
        // Given
        Prices domain = new Prices();
        PricesEntity entity = new PricesEntity();
        PricesEntity savedEntity = new PricesEntity();
        Prices savedDomain = new Prices();

        when(repository.save(entity)).thenReturn(savedEntity);

        // When
        Prices result = sutAdapter.createPrices(domain);

        // Then
        assertEquals(savedDomain, result);
        verify(repository).save(entity);
    }

    @Test
    void shouldReturnValidPricesByProductIdAndBrandId() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        int productId = 1;
        int brandId = 2;

        List<PricesEntity> entities = List.of(new PricesEntity(), new PricesEntity());
        List<Prices> domainList = List.of(new Prices(), new Prices());

        when(repository.getValidPricesByProductIdAndBrandId(now, productId, brandId)).thenReturn(entities);

        // When
        List<Prices> result = sutAdapter.getValidPricesByProductIdAndBrandId(now, productId, brandId);

        // Then
        assertEquals(domainList, result);
        verify(repository).getValidPricesByProductIdAndBrandId(now, productId, brandId);
    }
}

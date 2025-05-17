package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.rest.exceptions.PriceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPricesServiceTest {
    @InjectMocks
    private GetPricesService sutService;
    @Mock
    private PricesPort port;
    @Test
    void shouldReturnPriceWithHighestPriority() {
        // Given
        LocalDateTime date = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;

        List<Prices> pricesList = List.of(Prices.builder().priority(0).build(),
            Prices.builder().priority(1).build(),Prices.builder().priority(1).build());

        when(port.getValidPricesByProductIdAndBrandId(date, productId, brandId))
            .thenReturn(pricesList);

        // When
        Prices result = sutService.getValidPricesByProductIdAndBrandId(date, productId, brandId);

        // Then
        assertEquals(pricesList.get(1), result);
        verify(port, times(1)).getValidPricesByProductIdAndBrandId(date, productId, brandId);
    }

    @Test
    void shouldThrowExceptionWhenNoPricesFound() {
        // Given
        LocalDateTime date = LocalDateTime.now();
        Integer productId = 35455;
        Integer brandId = 1;

        when(port.getValidPricesByProductIdAndBrandId(date, productId, brandId))
            .thenReturn(Collections.emptyList());

        // When & Then
        assertThrows(PriceNotFoundException.class,
            () -> sutService.getValidPricesByProductIdAndBrandId(date, productId, brandId));

        verify(port, times(1)).getValidPricesByProductIdAndBrandId(date, productId, brandId);
    }
    
}

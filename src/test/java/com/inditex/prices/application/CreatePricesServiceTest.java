package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.PricesPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreatePricesServiceTest {
    @InjectMocks
    private CreatePricesService sutService;
    @Mock
    private PricesPort port;

    @Test
    public void createPriceService() {
        // Given
        Prices inputPrice = new Prices(); // ponle datos si es necesario
        Prices savedPrice = new Prices(); // resultado simulado del port
        when(port.createPrices(inputPrice)).thenReturn(savedPrice);

        // When
        Prices result = sutService.createPrice(inputPrice);

        // Then
        assertEquals(savedPrice, result);
        verify(port, times(1)).createPrices(inputPrice);
    }
}

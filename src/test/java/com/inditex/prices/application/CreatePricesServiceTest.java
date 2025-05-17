package com.inditex.prices.application;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.domain.ports.PricesPort;
import com.inditex.prices.infrastructure.rest.exceptions.InvalidCurrencyCodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Prices inputPrice = Prices.builder().currencyCode("EUR").build();
        Prices savedPrice = Prices.builder().currencyCode("EUR").build();
        when(port.createPrices(inputPrice)).thenReturn(savedPrice);

        // When
        Prices result = sutService.createPrice(inputPrice);

        // Then
        assertEquals(savedPrice, result);
        verify(port, times(1)).createPrices(inputPrice);
    }

    @Test
    public void createPriceServiceInvalidCurrencyCodeThrowsInvalidCurrencyCodeException() {
            // Given
           Prices prices = Prices.builder().currencyCode("XYZ").build();

            // When And Then
            InvalidCurrencyCodeException exception = assertThrows(
                InvalidCurrencyCodeException.class,
                () -> sutService.createPrice(prices)
            );

            assertEquals("Código de moneda inválido: XYZ", exception.getMessage());

    }
}

package com.inditex.prices.domain.ports;

import java.util.UUID;

public interface DeletePricesUseCase {
    void deletePriceById(Integer id);

}

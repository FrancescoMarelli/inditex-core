package com.inditex.prices.infrastructure.rest.mapper;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.rest.dto.PricesDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PricesRestMapper {

    PricesDto toDto(Prices prices);

    Prices toDomain(PricesDto pricesDto);

}

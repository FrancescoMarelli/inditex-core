package com.inditex.prices.infrastructure.persistence.mapper;

import com.inditex.prices.domain.model.Prices;
import com.inditex.prices.infrastructure.persistence.entity.PricesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricesPersistenceMapper {

    @Mapping(target = "curr", source = "currencyCode")
    PricesEntity toEntity(Prices prices);

    @Mapping(target = "currencyCode", source = "curr")
    Prices toDomain(PricesEntity entity);

    List<Prices> toDomainList(List<PricesEntity> promotionEntities);

}

package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.model.CartProduct;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartProductMapper {

    CartProductDto toDto(CartProduct cartProduct);
}

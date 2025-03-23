package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.model.CartProduct;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CartProductMapper {

    CartProductDto toDto(CartProduct cartProduct);

    @AfterMapping
    default void setProductId(@MappingTarget CartProductDto cartProductDto,
                              CartProduct cartProduct) {
        cartProductDto.setProductId(cartProduct.getProduct().getId());
    }
}

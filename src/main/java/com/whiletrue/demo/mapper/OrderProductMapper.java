package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.OrderProductDto;
import com.whiletrue.demo.model.OrderProduct;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface OrderProductMapper {

    @Mapping(target = "productId", source = "product.id")
    OrderProductDto toDto(OrderProduct orderProduct);

    @AfterMapping
    default void setProductId(@MappingTarget OrderProductDto orderProductDto,
                              OrderProduct orderProduct) {
        orderProductDto.setProductId(orderProduct.getProduct().getId());
    }
}

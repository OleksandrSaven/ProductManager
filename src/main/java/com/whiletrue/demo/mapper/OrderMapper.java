package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.OrderDto;
import com.whiletrue.demo.model.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = {OrderProductMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderProducts", source = "orderProducts")
    OrderDto toDto(Order order);

    @AfterMapping
    default void setUserId(@MappingTarget OrderDto orderDto, Order order) {
        orderDto.setUserId(order.getUser().getId());
    }
}

package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.model.Product;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {

    ProductDto toDto(Product product);
}

package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.model.Category;
import com.whiletrue.demo.model.Product;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ProductMapper {

    ProductDto toDto(Product product);

    @AfterMapping
    default void setCategoryIds(@MappingTarget ProductDto productDto, Product product) {
        if (product.getCategories() != null) {
            productDto.setCategoryIds(product.getCategories().stream()
                    .map(Category::getId).collect(Collectors.toSet()));
        }
    }

    Product toModel(CreateProductRequestDto createProductRequestDto);

    @AfterMapping
    default void setCategories(@MappingTarget Product product, CreateProductRequestDto requestDto) {
        if (requestDto.getCategoryIds() != null) {
            product.setCategories(requestDto.getCategoryIds().stream()
                    .map(Category::new)
                    .collect(Collectors.toSet()));
        }
    }
}

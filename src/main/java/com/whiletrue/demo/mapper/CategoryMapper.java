package com.whiletrue.demo.mapper;

import com.whiletrue.demo.config.MapperConfig;
import com.whiletrue.demo.dto.CategoryDto;
import com.whiletrue.demo.dto.CreateCategoryRequestDto;
import com.whiletrue.demo.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto createCategoryDto);
}

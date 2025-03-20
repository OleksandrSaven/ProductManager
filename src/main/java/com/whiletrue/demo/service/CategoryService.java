package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CategoryDto;
import com.whiletrue.demo.dto.CreateCategoryRequestDto;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto findById(Long id);

    CategoryDto create(CreateCategoryRequestDto createCategoryRequestDto);

    CategoryDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto);

    void deleteById(Long id);
}

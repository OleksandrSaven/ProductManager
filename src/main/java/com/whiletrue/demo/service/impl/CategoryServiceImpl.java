package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CategoryDto;
import com.whiletrue.demo.dto.CreateCategoryRequestDto;
import com.whiletrue.demo.exeption.EntityNotFoundException;
import com.whiletrue.demo.mapper.CategoryMapper;
import com.whiletrue.demo.model.Category;
import com.whiletrue.demo.repository.CategoryRepository;
import com.whiletrue.demo.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category with id " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto create(CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryMapper.toDto(categoryRepository.save(
                categoryMapper.toModel(createCategoryRequestDto)));
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto createCategoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category with id " + id));
        category.setName(createCategoryRequestDto.getName());
        category.setDescription(createCategoryRequestDto.getDescription());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

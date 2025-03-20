package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.CategoryDto;
import com.whiletrue.demo.dto.CreateCategoryRequestDto;
import com.whiletrue.demo.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CreateCategoryRequestDto
                                                  createCategoryRequestDto) {
        return categoryService.create(createCategoryRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody CreateCategoryRequestDto createCategoryRequestDto) {

        return categoryService.update(id, createCategoryRequestDto);
    }
}

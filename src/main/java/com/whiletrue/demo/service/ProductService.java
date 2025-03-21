package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import java.util.List;

public interface ProductService {

    ProductDto save(CreateProductRequestDto productRequestDto);

    ProductDto findById(Long id);

    void delete(Long id);

    ProductDto update(Long id, CreateProductRequestDto requestDto);

    List<ProductDto> findAll();
}

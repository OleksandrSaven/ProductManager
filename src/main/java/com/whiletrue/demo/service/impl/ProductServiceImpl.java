package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.repository.ProductRepository;
import com.whiletrue.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto save(CreateProductRequestDto productRequestDto) {
        // productRepository.save();
        return null;
    }
}

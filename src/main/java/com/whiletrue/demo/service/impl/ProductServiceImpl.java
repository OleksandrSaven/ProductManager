package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.exeption.EntityNotFoundException;
import com.whiletrue.demo.mapper.ProductMapper;
import com.whiletrue.demo.model.Product;
import com.whiletrue.demo.repository.ProductRepository;
import com.whiletrue.demo.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto save(CreateProductRequestDto productRequestDto) {
        return productMapper.toDto(
                productRepository.save(productMapper.toModel(productRequestDto)));
    }

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id " + id)));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto update(Long id, CreateProductRequestDto requestDto) {
        productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find entity with id " + id));
        Product model = productMapper.toModel(requestDto);
        model.setId(id);
        return productMapper.toDto(productRepository.save(model));
    }

    @Override
    public List<ProductDto> findAll() {
        return List.of();
    }
}

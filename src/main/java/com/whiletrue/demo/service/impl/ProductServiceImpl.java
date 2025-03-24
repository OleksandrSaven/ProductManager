package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;
import com.whiletrue.demo.exception.EntityNotFoundException;
import com.whiletrue.demo.mapper.ProductMapper;
import com.whiletrue.demo.model.Product;
import com.whiletrue.demo.repository.ProductRepository;
import com.whiletrue.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public ProductDto save(CreateProductRequestDto productRequestDto) {
        return productMapper.toDto(
                productRepository.save(productMapper.toModel(productRequestDto)));
    }

    @Override
    @Cacheable(value = "productsCache", key = "#id", unless = "#result == null")
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id " + id)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ProductDto update(Long id, CreateProductRequestDto requestDto) {
        productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find entity with id " + id));
        Product model = productMapper.toModel(requestDto);
        model.setId(id);
        return productMapper.toDto(productRepository.save(model));
    }

    @Override
    @Cacheable(value = "productsCache", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<ProductDto> findAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }
}

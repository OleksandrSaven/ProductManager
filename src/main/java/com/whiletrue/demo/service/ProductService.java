package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CreateProductRequestDto;
import com.whiletrue.demo.dto.ProductDto;

public interface ProductService {

    ProductDto save(CreateProductRequestDto productRequestDto);
}

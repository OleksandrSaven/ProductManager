package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CartProductDto;
import java.util.List;

public interface CartProductService {
    List<CartProductDto> findCartProductsByCart(Long id);
}

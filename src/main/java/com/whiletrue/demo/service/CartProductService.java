package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.dto.CreateCartProductRequestDto;
import com.whiletrue.demo.dto.UpdateCartItemQuantityDto;
import java.util.List;

public interface CartProductService {

    List<CartProductDto> findCartProductsByCart(Long id);

    CartProductDto save(CreateCartProductRequestDto requestDto);

    CartProductDto update(Long id, UpdateCartItemQuantityDto quantityDto);

    void delete(Long id);
}

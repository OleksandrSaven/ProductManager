package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CartDto;
import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.dto.CreateCartProductRequestDto;
import org.springframework.security.core.Authentication;

public interface CartService {

    CartDto findCart(Authentication authentication);

    CartProductDto save(CreateCartProductRequestDto requestDto);
}

package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.CartDto;
import org.springframework.security.core.Authentication;

public interface CartService {

    CartDto findCart(Authentication authentication);
}

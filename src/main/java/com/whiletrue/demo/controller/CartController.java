package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.CartDto;
import com.whiletrue.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public CartDto findCart(Authentication authentication) {
        return cartService.findCart(authentication);
    }
}

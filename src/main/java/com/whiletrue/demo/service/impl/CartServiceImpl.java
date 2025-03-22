package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CartDto;
import com.whiletrue.demo.exeption.EntityNotFoundException;
import com.whiletrue.demo.model.Cart;
import com.whiletrue.demo.model.User;
import com.whiletrue.demo.repository.CartRepository;
import com.whiletrue.demo.service.CartProductService;
import com.whiletrue.demo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartProductService cartProductService;

    @Override
    public CartDto findCart(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Cart cart = cartRepository.findByUserId(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart belong user with id "
                        + currentUser.getId()));
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(currentUser.getId());
        cartDto.setCartProducts(cartProductService.findCartProductsByCart(cart.getId()));
        return cartDto;
    }
}

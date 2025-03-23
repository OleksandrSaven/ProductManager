package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.CartDto;
import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.dto.CreateCartProductRequestDto;
import com.whiletrue.demo.dto.UpdateCartItemQuantityDto;
import com.whiletrue.demo.service.CartProductService;
import com.whiletrue.demo.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Cart management", description = "Endpoints for managing carts")
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartProductService cartProductService;

    @Operation(summary = "Show user cart")
    @GetMapping
    public CartDto findCart(Authentication authentication) {
        return cartService.findCart(authentication);
    }

    @Operation(summary = "Add products to cart", description = "Return cart with product")
    @PostMapping
    public CartProductDto addCartProducts(@RequestBody @Valid
                                              CreateCartProductRequestDto requestDto) {
        return cartService.save(requestDto);
    }

    @Operation(summary = "Update quantity of product", description = "Return updated cart")
    @PatchMapping("cart-product/{id}")
    public CartProductDto update(@PathVariable Long id,
                                 @Valid @RequestBody UpdateCartItemQuantityDto
                                         updateCartItemQuantityDto) {
        return cartProductService.update(id, updateCartItemQuantityDto);
    }

    @Operation(summary = "Delete product from cart")
    @DeleteMapping("cart-product/{id}")
    public void delete(@PathVariable Long id) {
        cartProductService.delete(id);
    }
}

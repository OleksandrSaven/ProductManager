package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.dto.CreateCartProductRequestDto;
import com.whiletrue.demo.dto.UpdateCartItemQuantityDto;
import com.whiletrue.demo.exeption.EntityNotFoundException;
import com.whiletrue.demo.mapper.CartProductMapper;
import com.whiletrue.demo.model.Cart;
import com.whiletrue.demo.model.CartProduct;
import com.whiletrue.demo.repository.CartProductRepository;
import com.whiletrue.demo.repository.CartRepository;
import com.whiletrue.demo.repository.ProductRepository;
import com.whiletrue.demo.service.CartProductService;
import com.whiletrue.demo.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final CartProductMapper cartProductMapper;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Override
    public List<CartProductDto> findCartProductsByCart(Long id) {
        return cartProductRepository.findCartProductByCartId(id)
                .stream().map(cartProductMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CartProductDto save(CreateCartProductRequestDto requestDto) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(productRepository.findById(requestDto.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find product with id "
                        + requestDto.getProductId())));
        cartProduct.setQuantity(requestDto.getQuantity());
        Long userId = userService.getAuthenticatedUser().getId();
        Cart cart = cartRepository.findByUserId(userId).get();
        setCartAndCartProduct(cart, cartProduct);
        return cartProductMapper.toDto(cartProductRepository.save(cartProduct));
    }

    @Override
    public CartProductDto update(Long id, UpdateCartItemQuantityDto quantityDto) {
        CartProduct cartProduct = cartProductRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find cartProduct with id " + id));
        cartProduct.setQuantity(quantityDto.getQuantity());
        return cartProductMapper.toDto(cartProductRepository.save(cartProduct));
    }

    @Override
    public void delete(Long id) {
        Long userId = userService.getAuthenticatedUser().getId();
        cartProductRepository.delete(
                cartProductRepository.findCartProductByCartIdAndUserId(id, userId));
    }

    private void setCartAndCartProduct(Cart cart, CartProduct cartProduct) {
        cartProduct.setCart(cart);
        Set<CartProduct> cartProducts = new HashSet<>();
        cartProducts.add(cartProduct);
        if (cart.getCartProducts().isEmpty()) {
            cart.setCartProducts(cartProducts);
        } else {
            cart.getCartProducts().add(cartProduct);
        }
    }
}

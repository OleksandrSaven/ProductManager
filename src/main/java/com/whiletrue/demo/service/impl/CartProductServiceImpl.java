package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.CartProductDto;
import com.whiletrue.demo.mapper.CartProductMapper;
import com.whiletrue.demo.repository.CartProductRepository;
import com.whiletrue.demo.service.CartProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartProductServiceImpl implements CartProductService {
    private final CartProductRepository cartProductRepository;
    private final CartProductMapper cartProductMapper;

    @Override
    public List<CartProductDto> findCartProductsByCart(Long id) {
        return cartProductRepository.findCartProductByCartId(id)
                .stream().map(cartProductMapper::toDto)
                .toList();
    }
}

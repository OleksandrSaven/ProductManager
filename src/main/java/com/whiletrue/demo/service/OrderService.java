package com.whiletrue.demo.service;

import com.whiletrue.demo.dto.OrderDto;
import com.whiletrue.demo.dto.OrderStatusDto;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface OrderService {

    OrderDto create(Authentication authentication);

    List<OrderDto> findAll();

    List<OrderDto> findMy(Authentication authentication);

    List<OrderDto> findByUserId(Long userId);

    OrderDto findById(Long id);

    OrderDto updateStatus(Long id, OrderStatusDto orderStatusDto);
}

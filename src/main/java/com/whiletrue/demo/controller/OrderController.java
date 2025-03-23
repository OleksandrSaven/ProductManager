package com.whiletrue.demo.controller;

import com.whiletrue.demo.dto.OrderDto;
import com.whiletrue.demo.dto.OrderStatusDto;
import com.whiletrue.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Create new order",
            description = "Return created order")
    @PostMapping
    public OrderDto createOrder(Authentication authentication) {
        return orderService.create(authentication);
    }

    @Operation(summary = "Get all my orders", description = "Get a list of all my orders")
    @GetMapping("/my")
    public List<OrderDto> findMy(Authentication authentication) {
        return orderService.findMy(authentication);
    }

    @Operation(summary = "Get all orders by user", description = "Get a list of all user orders")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/{userId}")
    public List<OrderDto> findAllByUser(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    @Operation(summary = "Get order by id", description = "Return specific order")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public OrderDto findOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @Operation(summary = "Get all orders for all users",
            description = "Get a list of all users orders")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @Operation(summary = "Change order status", description = "Change order status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public OrderDto updateStatus(@PathVariable Long id,
                                 @RequestBody OrderStatusDto orderStatusDto) {
        return orderService.updateStatus(id, orderStatusDto);
    }
}

package com.whiletrue.demo.service.impl;

import com.whiletrue.demo.dto.OrderDto;
import com.whiletrue.demo.dto.OrderStatusDto;
import com.whiletrue.demo.exception.EntityNotFoundException;
import com.whiletrue.demo.exception.ValidationException;
import com.whiletrue.demo.mapper.OrderMapper;
import com.whiletrue.demo.model.Cart;
import com.whiletrue.demo.model.Order;
import com.whiletrue.demo.model.OrderProduct;
import com.whiletrue.demo.model.Status;
import com.whiletrue.demo.model.User;
import com.whiletrue.demo.repository.CartProductRepository;
import com.whiletrue.demo.repository.CartRepository;
import com.whiletrue.demo.repository.OrderProductRepository;
import com.whiletrue.demo.repository.OrderRepository;
import com.whiletrue.demo.repository.UserRepository;
import com.whiletrue.demo.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderMapper orderMapper;
    private final CartProductRepository cartProductRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderDto create(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Cart cart = cartRepository.findByUserId(currentUser.getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart belong user with id "
                        + currentUser.getId()));
        if (cart.getCartProducts().isEmpty()) {
            throw new ValidationException("Your cart is empty!");
        }

        Order order = makeOrder(cart, currentUser);
        return orderMapper.toDto(order);
    }

    @Override
    @Cacheable(value = "ordersCache", key = "'allOrders'", unless = "#result == null")
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                 .map(orderMapper::toDto)
                 .toList();
    }

    @Override
    @Cacheable(value = "userOrdersCache", key = "#authentication.principal.id",
            unless = "#result == null")
    public List<OrderDto> findMy(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return orderRepository.findOrderByUserId(currentUser.getId()).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(value = "userOrdersCache", key = "#userId", unless = "#result == null")
    public List<OrderDto> findByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find user with id " + userId));
        return orderRepository.findOrderByUserId(userId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Cacheable(value = "ordersCache", key = "#id", unless = "#result == null")
    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id " + id)));
    }

    @Override
    @Transactional
    public OrderDto updateStatus(Long id, OrderStatusDto orderStatusDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find order with id " + id));
        order.setStatus(Status.valueOf(orderStatusDto.getStatus()));
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order makeOrder(Cart cart, User user) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setUser(user);
        order.setTotal(calculateTotal(cart));
        Order savedOrder = orderRepository.save(order);
        List<OrderProduct> orderProducts = orderProductRepository.saveAll(
                mapCartProductToOrderProduct(cart, savedOrder));
        order.setOrderProducts(new HashSet<>(orderProducts));
        cartProductRepository.deleteAll(cart.getCartProducts());
        cart.getCartProducts().clear();
        cartRepository.save(cart);
        return savedOrder;
    }

    private Set<OrderProduct> mapCartProductToOrderProduct(Cart cart, Order order) {
        return cart.getCartProducts().stream().map(cartProduct -> new OrderProduct(
                order,
                cartProduct.getProduct(),
                cartProduct.getQuantity(),
                cartProduct.getProduct().getPrice()
        )).collect(Collectors.toSet());
    }

    private BigDecimal calculateTotal(Cart cart) {
        return cart.getCartProducts().stream()
                .map(x -> x.getProduct().getPrice().multiply(BigDecimal.valueOf(x.getQuantity())))
                .reduce(BigDecimal::add).orElseThrow(
                        () -> new ValidationException("Can't calculate total"));
    }
}

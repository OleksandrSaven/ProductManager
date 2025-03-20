package com.whiletrue.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private Long id;
    private User user;
    private LocalDateTime orderData;
    private BigDecimal total;
    private Status status;
    private Set<OrderItem> orderItems = new HashSet<>();

}

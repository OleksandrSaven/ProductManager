package com.whiletrue.demo.model;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;
    private Order order;
    private int quantity;
    private Product product;
    private BigDecimal price;
}

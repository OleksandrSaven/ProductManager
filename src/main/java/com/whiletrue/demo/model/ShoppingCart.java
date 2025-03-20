package com.whiletrue.demo.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCart {
    private Long id;
    private User user;
    private Set<CartItem> cartItems = new HashSet<>();
}

package com.whiletrue.demo.dto;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private Long productId;
    private int quantity;
}

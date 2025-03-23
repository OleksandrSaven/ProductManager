package com.whiletrue.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderProductDto {
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal price;
}

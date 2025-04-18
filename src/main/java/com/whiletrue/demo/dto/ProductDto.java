package com.whiletrue.demo.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
}

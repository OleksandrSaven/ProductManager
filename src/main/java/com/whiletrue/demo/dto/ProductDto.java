package com.whiletrue.demo.dto;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private Set<Long> categoryIds;
}

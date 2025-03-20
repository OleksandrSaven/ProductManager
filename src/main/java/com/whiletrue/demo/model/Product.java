package com.whiletrue.demo.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imgUrl;
    private List<Category> categories;
}

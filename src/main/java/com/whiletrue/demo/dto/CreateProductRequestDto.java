package com.whiletrue.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateProductRequestDto {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    @NotBlank
    private String description;
    @Min(0)
    private BigDecimal price;
    private String imgUrl;
}

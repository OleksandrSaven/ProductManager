package com.whiletrue.demo.dto;

import com.whiletrue.demo.model.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private Status status;
    private Set<OrderProductDto> orderProducts = new HashSet<>();
}

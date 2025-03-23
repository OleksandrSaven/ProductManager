package com.whiletrue.demo.repository;

import com.whiletrue.demo.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByUserId(Long id);
}

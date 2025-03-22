package com.whiletrue.demo.repository;

import com.whiletrue.demo.model.CartProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findCartProductByCartId(Long id);
}

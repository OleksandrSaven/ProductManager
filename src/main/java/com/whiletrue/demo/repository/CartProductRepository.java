package com.whiletrue.demo.repository;

import com.whiletrue.demo.model.CartProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    List<CartProduct> findCartProductByCartId(Long id);

    @Query("FROM CartProduct cp WHERE cp.id = :productId AND cp.cart.user.id = :userId")
    CartProduct findCartProductByCartIdAndUserId(Long productId, Long userId);
}

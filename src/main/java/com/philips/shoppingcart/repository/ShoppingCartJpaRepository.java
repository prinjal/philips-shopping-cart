package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJpaRepository extends JpaRepository<ShoppingCart,Long> {
}

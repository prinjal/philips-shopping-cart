package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartJpaInterface extends JpaRepository<ShoppingCart,Integer> {
}

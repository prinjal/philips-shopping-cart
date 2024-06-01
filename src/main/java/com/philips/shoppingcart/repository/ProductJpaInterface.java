package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaInterface extends JpaRepository<Product,Integer> {
}

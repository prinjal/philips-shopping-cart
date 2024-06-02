package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product,Long> {
    boolean existsProductByName(String name);
}

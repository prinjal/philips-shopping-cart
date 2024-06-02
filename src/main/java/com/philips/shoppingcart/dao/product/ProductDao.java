package com.philips.shoppingcart.dao.product;

import com.philips.shoppingcart.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product createOrUpdateProduct(Product product);
    void deleteProduct(Product product);

    boolean productExists(String name);
}

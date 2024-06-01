package com.philips.shoppingcart.dao.product;

import com.philips.shoppingcart.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

    boolean productExists(String name);
}

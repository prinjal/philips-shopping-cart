package com.philips.shoppingcart.dao.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ProductJdbcDao")
public class ProductJdbcDataAccessImpl implements ProductDao {
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public boolean productExists(String name) {
        return false;
    }
}

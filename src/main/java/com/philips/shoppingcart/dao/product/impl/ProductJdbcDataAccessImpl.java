package com.philips.shoppingcart.dao.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ProductJdbcDao")
public class ProductJdbcDataAccessImpl implements ProductDao {
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return null;
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        return null;
    }


    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public boolean productExists(String name) {
        return false;
    }
}

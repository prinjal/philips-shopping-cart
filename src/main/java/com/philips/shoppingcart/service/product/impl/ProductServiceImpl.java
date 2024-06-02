package com.philips.shoppingcart.service.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.createProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return productDao.updateProduct(id,product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    @Override
    public boolean productExists(String name) {
        return productDao.productExists(name);
    }
}

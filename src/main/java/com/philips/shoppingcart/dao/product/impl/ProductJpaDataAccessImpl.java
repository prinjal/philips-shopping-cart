package com.philips.shoppingcart.dao.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ProductJpaDao")
@Primary
@RequiredArgsConstructor
public class ProductJpaDataAccessImpl implements ProductDao {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<Product> getAllProducts() {
        return productJpaRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productJpaRepository.findById(id);
    }

    @Override
    public Product createOrUpdateProduct(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productJpaRepository.delete(product);
    }

    @Override
    public boolean productExists(String name) {
        return productJpaRepository.existsProductByName(name);
    }
}

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
    public Product createProduct(Product product) {
        return productJpaRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct=productJpaRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            return productJpaRepository.save(existingProduct);
        }
        else{
            throw new ResourceNotFound("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct=productJpaRepository.findById(id);
        if (optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            productJpaRepository.delete(existingProduct);
        }
        else{
            throw new ResourceNotFound("Product not found");
        }
    }

    @Override
    public boolean productExists(String name) {
        return productJpaRepository.existsProductByName(name);
    }
}

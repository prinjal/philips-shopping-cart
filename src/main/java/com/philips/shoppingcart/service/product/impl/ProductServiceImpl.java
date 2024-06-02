package com.philips.shoppingcart.service.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
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
    public Product getProductById(Long id) {
        return productDao.getProductById(id).orElseThrow(() -> new ResourceNotFound("Shopping Cart not found"));
    }

    @Override
    public Product createProduct(Product product) {
        return productDao.createOrUpdateProduct(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        return productDao.createOrUpdateProduct(existingProduct);  // Save updated product
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = getProductById(id);
        productDao.deleteProduct(existingProduct);
    }

    @Override
    public boolean productExists(String name) {
        return productDao.productExists(name);
    }
}

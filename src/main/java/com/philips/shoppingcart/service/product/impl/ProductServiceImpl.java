package com.philips.shoppingcart.service.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dto.ProductDto;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return products.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found"));
        return convertToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        Product savedProduct = productDao.createOrUpdateProduct(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found"));

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());

        Product updatedProduct = productDao.createOrUpdateProduct(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found"));
        productDao.deleteProduct(existingProduct);
    }

    @Override
    public boolean productExists(String name) {
        return productDao.productExists(name);
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getName(), product.getPrice());
    }
}

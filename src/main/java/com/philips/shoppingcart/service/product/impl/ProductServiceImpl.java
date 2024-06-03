package com.philips.shoppingcart.service.product.impl;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dto.product.RequestProductDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
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
    public List<ResponseProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseProductDto getProductById(Long id) {
        Product product = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id: " + id));
        return convertToDto(product);
    }

    @Override
    public ResponseProductDto createProduct(RequestProductDto product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());

        Product savedProduct = productDao.createOrUpdateProduct(newProduct);
        return convertToDto(savedProduct);
    }

    @Override
    public ResponseProductDto updateProduct(Long id, RequestProductDto product) {
        Product existingProduct = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());

        Product updatedProduct = productDao.createOrUpdateProduct(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productDao.getProductById(id)
                .orElseThrow(() -> new ResourceNotFound("Product not found with id: " + id));
        productDao.deleteProduct(existingProduct);
    }

    @Override
    public boolean productExists(String name) {
        return productDao.productExists(name);
    }

    private ResponseProductDto convertToDto(Product product) {
        return new ResponseProductDto(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}

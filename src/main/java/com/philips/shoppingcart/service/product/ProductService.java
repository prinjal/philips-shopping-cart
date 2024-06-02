package com.philips.shoppingcart.service.product;

import com.philips.shoppingcart.dto.ProductDto;
import com.philips.shoppingcart.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(Long id, ProductDto product);
    void deleteProduct(Long id);

    boolean productExists(String name);
}

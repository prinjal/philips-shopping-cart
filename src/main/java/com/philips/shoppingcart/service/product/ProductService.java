package com.philips.shoppingcart.service.product;

import com.philips.shoppingcart.dto.product.RequestProductDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;

import java.util.List;

public interface ProductService {
    List<ResponseProductDto> getAllProducts();
    ResponseProductDto getProductById(Long id);
    ResponseProductDto createProduct(RequestProductDto product);
    ResponseProductDto updateProduct(Long id, RequestProductDto product);
    void deleteProduct(Long id);

    boolean productExists(String name);
}

package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.product.RequestProductDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> getAllProducts() {
        List<ResponseProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> getProductById(@PathVariable Long id) {
        ResponseProductDto product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ResponseProductDto> createProduct(@RequestBody RequestProductDto product) {
        ResponseProductDto createdProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDto> updateProduct(@PathVariable Long id, @RequestBody RequestProductDto product) {
        ResponseProductDto updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

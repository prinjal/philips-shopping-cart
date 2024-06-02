package com.philips.shoppingcart.service.product;

import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dto.ProductDto;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.product.impl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductDao productDao;


    private ProductServiceImpl productService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllProducts() {
        // Given
        Product product1 = new Product(1L, "Product 1", 10.0);
        Product product2 = new Product(2L, "Product 2", 20.0);
        List<Product> products = Arrays.asList(product1, product2);
        when(productDao.getAllProducts()).thenReturn(products);

        // When
        List<ProductDto> retrievedProducts = productService.getAllProducts();

        // Then
        assertThat(retrievedProducts).hasSize(2);
        assertThat(retrievedProducts).extracting("name").containsExactlyInAnyOrder("Product 1", "Product 2");
        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L;
        Product product = new Product(productId, "Product 1", 10.0);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        ProductDto retrievedProduct = productService.getProductById(productId);

        // Then
        assertThat(retrievedProduct.getName()).isEqualTo(product.getName());
        assertThat(retrievedProduct.getPrice()).isEqualTo(product.getPrice());
        verify(productDao, times(1)).getProductById(productId);
    }

    @Test
    void createProduct() {
        // Given
        ProductDto productDto = new ProductDto("New Product", 10.0);
        Product product = new Product(null, productDto.getName(), productDto.getPrice());
        when(productDao.createOrUpdateProduct(any(Product.class))).thenReturn(product);

        // When
        ProductDto createdProduct = productService.createProduct(productDto);

        // Then
        assertThat(createdProduct.getName()).isEqualTo(productDto.getName());
        assertThat(createdProduct.getPrice()).isEqualTo(productDto.getPrice());
        verify(productDao, times(1)).createOrUpdateProduct(any(Product.class));
    }

    @Test
    void updateProduct() {
        // Given
        Long productId = 1L;
        ProductDto productDto = new ProductDto("Updated Product", 20.0);
        Product existingProduct = new Product(productId, "Old Product", 10.0);
        Product updatedProduct = new Product(productId, productDto.getName(), productDto.getPrice());

        when(productDao.getProductById(productId)).thenReturn(Optional.of(existingProduct));
        when(productDao.createOrUpdateProduct(any(Product.class))).thenReturn(updatedProduct);

        // When
        ProductDto result = productService.updateProduct(productId, productDto);

        // Then
        assertThat(result.getName()).isEqualTo(productDto.getName());
        assertThat(result.getPrice()).isEqualTo(productDto.getPrice());
        verify(productDao, times(1)).getProductById(productId);
        verify(productDao, times(1)).createOrUpdateProduct(any(Product.class));
    }

    @Test
    void deleteProduct() {
        // Given
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Product", 10.0);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(existingProduct));

        // When
        productService.deleteProduct(productId);

        // Then
        verify(productDao, times(1)).getProductById(productId);
        verify(productDao, times(1)).deleteProduct(existingProduct);
    }

    @Test
    void productExists() {
        // Given
        String productName = "Test";
        when(productDao.productExists(productName)).thenReturn(true);

        // When
        boolean exists = productService.productExists(productName);

        // Then
        assertThat(exists).isTrue();
        verify(productDao, times(1)).productExists(productName);
    }
}

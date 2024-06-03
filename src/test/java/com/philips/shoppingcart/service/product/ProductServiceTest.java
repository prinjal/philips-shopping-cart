package com.philips.shoppingcart.service.product;

import com.philips.shoppingcart.dao.product.ProductDao;
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
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);
        List<Product> products = Arrays.asList(product1, product2);
        when(productDao.getAllProducts()).thenReturn(products);

        // When
        List<Product> retrievedProducts = productService.getAllProducts();

        // Then
        assertThat(retrievedProducts).hasSize(2);
        assertThat(retrievedProducts).containsExactlyInAnyOrder(product1, product2);
        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        Product retrievedProduct = productService.getProductById(productId);

        // Then
        assertThat(retrievedProduct).isEqualTo(product);
        verify(productDao, times(1)).getProductById(productId);
    }

    @Test
    void createProduct() {
        // Given
        Product product = new Product();
        product.setName("New Product");
        product.setPrice(10.0);
        when(productDao.createOrUpdateProduct(product)).thenReturn(product);

        // When
        Product createdProduct = productService.createProduct(product);

        // Then
        assertThat(createdProduct).isEqualTo(product);
        verify(productDao, times(1)).createOrUpdateProduct(product);
    }

    @Test
    void updateProduct() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("Updated Product");
        product.setPrice(20.0);
        when(productDao.createOrUpdateProduct(product)).thenReturn(product);

        // When
        Product updatedProduct = productService.updateProduct(product.getId(),product);

        // Then
        assertThat(updatedProduct).isEqualTo(product);
        verify(productDao, times(1)).createOrUpdateProduct(product);
    }

    @Test
    void deleteProduct() {
        // Given
        Product product = new Product();
        product.setName("New Product");
        product.setPrice(10.0);

        // When
        productService.deleteProduct(product.getId());

        // Then
        verify(productDao, times(1)).deleteProduct(product);
    }

    @Test
    void productExists() {
        // Given
        Long productId = 1L;
        String productName="Test";
        when(productDao.productExists(productName)).thenReturn(true);

        // When
        boolean exists = productService.productExists(productName);

        // Then
        assertThat(exists).isTrue();
        verify(productDao, times(1)).productExists(productName);
    }
}

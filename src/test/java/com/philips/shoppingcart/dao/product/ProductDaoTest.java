package com.philips.shoppingcart.dao.product;

import com.philips.shoppingcart.dao.product.impl.ProductJpaDataAccessImpl;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.repository.ProductJpaRepository;
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

class ProductDaoTest {

    private ProductDao testProductJpaDataAccess;
    private AutoCloseable autoCloseable;

    @Mock
    private ProductJpaRepository testProductJpaRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.testProductJpaDataAccess = new ProductJpaDataAccessImpl(testProductJpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllProducts() {
        // Given
        Product product1 = new Product("Product1", 10.0);
        Product product2 = new Product("Product2", 20.0);
        List<Product> products = Arrays.asList(product1, product2);
        when(testProductJpaRepository.findAll()).thenReturn(products);

        // When
        List<Product> retrievedProducts = testProductJpaDataAccess.getAllProducts();

        // Then
        assertThat(retrievedProducts).hasSize(2);
        assertThat(retrievedProducts).containsExactlyInAnyOrder(product1, product2);
        verify(testProductJpaRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L;
        Product product = new Product("Product", 10.0);
        product.setId(productId);
        when(testProductJpaRepository.findById(productId)).thenReturn(Optional.of(product));

        // When
        Optional<Product> retrievedProduct = testProductJpaDataAccess.getProductById(productId);

        // Then
        assertThat(retrievedProduct).isPresent();
        assertThat(retrievedProduct.get().getId()).isEqualTo(productId);
        verify(testProductJpaRepository, times(1)).findById(productId);
    }

    @Test
    void createProduct() {
        // Given
        Product product = new Product("Product", 10.0);
        when(testProductJpaRepository.save(product)).thenReturn(product);

        // When
        Product createdProduct = testProductJpaDataAccess.createProduct(product);

        // Then
        assertThat(createdProduct).isEqualTo(product);
        verify(testProductJpaRepository, times(1)).save(product);
    }

    @Test
    void updateProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product("Product", 10.0);
        product.setId(productId);
        when(testProductJpaRepository.save(product)).thenReturn(product);

        // When
        Product updatedProduct = testProductJpaDataAccess.updateProduct(productId,product);

        // Then
        assertThat(updatedProduct).isEqualTo(product);
        verify(testProductJpaRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct() {
        // Given
        Long productId = 1L;

        // When
        testProductJpaDataAccess.deleteProduct(productId);

        // Then
        verify(testProductJpaRepository, times(1)).deleteById(productId);
    }

    @Test
    void productExists() {
        // Given
        Long productId = 1L;
        when(testProductJpaRepository.existsById(productId)).thenReturn(true);

        // When
        boolean exists = testProductJpaDataAccess.productExists(String.valueOf(productId));

        // Then
        assertThat(exists).isTrue();
        verify(testProductJpaRepository, times(1)).existsById(productId);
    }
}

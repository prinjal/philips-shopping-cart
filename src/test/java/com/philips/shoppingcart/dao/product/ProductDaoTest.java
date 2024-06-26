package com.philips.shoppingcart.dao.product;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.product.impl.ProductJpaDataAccessImpl;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.repository.ProductJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductDaoTest extends AbstractTestContainer {

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
        Product product1 = new Product(1L,"Product1", 10.0);
        Product product2 = new Product(1L,"Product2", 20.0);
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
        Product product = new Product(1L,"Product", 10.0);
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
        Product product = new Product(1L,"Product", 10.0);
        when(testProductJpaRepository.save(product)).thenReturn(product);

        // When
        Product createdProduct = testProductJpaDataAccess.createOrUpdateProduct(product);

        // Then
        assertThat(createdProduct).isEqualTo(product);
        verify(testProductJpaRepository, times(1)).save(product);
    }


    @Test
    void deleteProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product(
                productId,
                "Test",
                10.0
        );

        // When
        testProductJpaDataAccess.deleteProduct(product);

        // Then
        verify(testProductJpaRepository, times(1)).delete(product);
    }

    @Test
    void productExists() {
        // Given
        String productName = "Test";
        when(testProductJpaRepository.existsProductByName(productName)).thenReturn(true);

        // When
        boolean exists = testProductJpaDataAccess.productExists(productName);

        // Then
        assertThat(exists).isTrue();
        verify(testProductJpaRepository, times(1)).existsProductByName(productName);
    }
}

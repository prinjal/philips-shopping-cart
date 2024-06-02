package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
class ProductJpaInterfaceTest {

    private static ProductJpaRepository testProductJpaRepository;

    @BeforeAll
    public static void setup(){
        testProductJpaRepository.deleteAll();
    }

    @Test
    void existsProductById() {
        // Given
        Product product = new Product("Test", 10.0);
        product = testProductJpaRepository.save(product);

        // When
        boolean exists = testProductJpaRepository.existsProductById(product.getId());

        // Then
        assertThat(exists).isTrue();
    }
}
package com.philips.shoppingcart.repository.product;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.repository.ProductJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductJpaInterfaceTest extends AbstractTestContainer{

    @Autowired
    private ProductJpaRepository testProductJpaRepository;

    @Test
    void existsProductById() {
        // Given
        Product product = new Product(1L,"Test", 10.0);
        product = testProductJpaRepository.save(product);

        // When
        boolean exists = testProductJpaRepository.existsProductByName(product.getName());

        // Then
        assertThat(exists).isTrue();
    }
}
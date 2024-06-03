package com.philips.shoppingcart.dao.shoppingcart;

import com.philips.shoppingcart.dao.shoppingcart.impl.ShoppingCartJpaDataAccessImpl;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.repository.ShoppingCartJpaRepository;
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

class ShoppingCartDaoTest {

    private ShoppingCartDao testShoppingCartJpaDataAccess;
    private AutoCloseable autoCloseable;

    @Mock
    private ShoppingCartJpaRepository testShoppingCartJpaRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.testShoppingCartJpaDataAccess = new ShoppingCartJpaDataAccessImpl(testShoppingCartJpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getShoppingCartById() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        when(testShoppingCartJpaRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // When
        Optional<ShoppingCart> retrievedCart = testShoppingCartJpaDataAccess.getShoppingCartById(cartId);

        // Then
        assertThat(retrievedCart).isPresent();
        assertThat(retrievedCart.get().getId()).isEqualTo(cartId);
        verify(testShoppingCartJpaRepository, times(1)).findById(cartId);
    }

    @Test
    void addItemToCart() {
        // Given
        Long cartId = 1L;
        Long productId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);

        Product product = new Product(productId, "Product", 100.0);
        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(5);
        item.setShoppingCart(cart);

        when(testShoppingCartJpaRepository.findById(cartId)).thenReturn(Optional.of(cart));
        when(testShoppingCartJpaRepository.save(cart)).thenAnswer(invocation -> {
            ShoppingCart savedCart = invocation.getArgument(0);
            savedCart.getItems().add(item);
            return savedCart;
        });

        // When
        cart.getItems().add(item);
        ShoppingCart updatedCart = testShoppingCartJpaDataAccess.saveShoppingCart(cart);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).contains(item);
        verify(testShoppingCartJpaRepository, times(1)).save(cart);
    }


}

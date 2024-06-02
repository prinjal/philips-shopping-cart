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
        this.testShoppingCartJpaDataAccess = new ShoppingCartJpaDataAccessImpl();
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
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));

        // When
        Optional<ShoppingCart> retrievedCart = Optional.ofNullable(testShoppingCartJpaDataAccess.getShoppingCartById(cartId));

        // Then
        assertThat(retrievedCart).isPresent();
        assertThat(retrievedCart.get().getId()).isEqualTo(cartId);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
    }

    @Test
    void addItemToCart() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item = new Item();
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));
        when(testShoppingCartJpaRepository.save(cart)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = testShoppingCartJpaDataAccess.addItemToCart(cartId, item).getShoppingCart();

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).contains(item);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
        verify(testShoppingCartJpaRepository, times(1)).save(cart);
    }

    @Test
    void updateItemInCart() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item = new Item();
        item.setId(1L);
        cart.getItems().add(item);
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));
        when(testShoppingCartJpaRepository.save(cart)).thenReturn(cart);

        // When
        ShoppingCart updatedCart=testShoppingCartJpaDataAccess.updateItemInCart(cartId, item.getId(),item);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).contains(item);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
        verify(testShoppingCartJpaRepository, times(1)).save(cart);
    }

    @Test
    void removeItemFromCart() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item = new Item();
        item.setId(1L);
        cart.getItems().add(item);
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));
        when(testShoppingCartJpaRepository.save(cart)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = testShoppingCartJpaDataAccess.removeItemFromCart(cartId, item.getId());

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).doesNotContain(item);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
        verify(testShoppingCartJpaRepository, times(1)).save(cart);
    }

    @Test
    void getAllItemsInCart() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);
        cart.getItems().addAll(Arrays.asList(item1, item2));
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));

        // When
        List<Item> retrievedItems = testShoppingCartJpaDataAccess.getAllItemsInCart(cartId);

        // Then
        assertThat(retrievedItems).hasSize(2);
        assertThat(retrievedItems).containsExactlyInAnyOrder(item1, item2);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
    }

    @Test
    void updateCartTotals() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item1 = new Item();
        item1.setQuantity(2);
        item1.setProduct(new Product("Product1", 10.0));
        Item item2 = new Item();
        item2.setQuantity(1);
        item2.setProduct(new Product("Product2", 20.0));
        cart.getItems().addAll(Arrays.asList(item1, item2));
        when(testShoppingCartJpaRepository.findById(Math.toIntExact(cartId))).thenReturn(Optional.of(cart));
        when(testShoppingCartJpaRepository.save(cart)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = testShoppingCartJpaDataAccess.updateCartTotals(cartId);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getTotalItems()).isEqualTo(3);
        assertThat(updatedCart.getTotalPrice()).isEqualTo(40.0);
        verify(testShoppingCartJpaRepository, times(1)).findById(Math.toIntExact(cartId));
        verify(testShoppingCartJpaRepository, times(1)).save(cart);
    }
}

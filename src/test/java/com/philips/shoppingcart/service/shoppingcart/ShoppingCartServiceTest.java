package com.philips.shoppingcart.service.shoppingcart;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartDao shoppingCartDao;

    private ShoppingCartServiceImpl shoppingCartService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        shoppingCartService = new ShoppingCartServiceImpl();  // Manually instantiate and inject mock
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
        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(cart);

        // When
        ShoppingCart retrievedCart = shoppingCartService.getShoppingCartById(cartId);

        // Then
        assertThat(retrievedCart).isEqualTo(cart);
        verify(shoppingCartDao, times(1)).getShoppingCartById(cartId);
    }

    @Test
    void addItemToCart() {
        // Given
        Long cartId = 1L;
        Item item = new Item();
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        cart.getItems().add(item);
        when(shoppingCartDao.addItemToCart(cartId, item)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = shoppingCartService.addItemToCart(cartId, item);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).contains(item);
        verify(shoppingCartDao, times(1)).addItemToCart(cartId, item);
    }

    @Test
    void updateItemInCart() {
        // Given
        Long cartId = 1L;
        Item item = new Item();
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        cart.getItems().add(item);
        when(shoppingCartDao.updateItemInCart(cartId, item.getId(), item)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = shoppingCartService.updateItemInCart(cartId,item.getId(), item);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).contains(item);
        verify(shoppingCartDao, times(1)).updateItemInCart(cartId,item.getId(), item);
    }

    @Test
    void removeItemFromCart() {
        // Given
        Long cartId = 1L;
        Long itemId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item = new Item();
        item.setId(itemId);
        cart.getItems().add(item);
        when(shoppingCartDao.removeItemFromCart(cartId, itemId)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = shoppingCartService.removeItemFromCart(cartId, itemId);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getItems()).doesNotContain(item);
        verify(shoppingCartDao, times(1)).removeItemFromCart(cartId, itemId);
    }

    @Test
    void getAllItemsInCart() {
        // Given
        Long cartId = 1L;
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);
        List<Item> items = Arrays.asList(item1, item2);
        when(shoppingCartDao.getAllItemsInCart(cartId)).thenReturn(items);

        // When
        List<Item> retrievedItems = shoppingCartService.getAllItemsInCart(cartId);

        // Then
        assertThat(retrievedItems).hasSize(2);
        assertThat(retrievedItems).containsExactlyInAnyOrder(item1, item2);
        verify(shoppingCartDao, times(1)).getAllItemsInCart(cartId);
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
        when(shoppingCartDao.updateCartTotals(cartId)).thenReturn(cart);

        // When
        ShoppingCart updatedCart = shoppingCartService.updateCartTotals(cart);

        // Then
        assertThat(updatedCart).isEqualTo(cart);
        assertThat(updatedCart.getTotalItems()).isEqualTo(3);
        assertThat(updatedCart.getTotalPrice()).isEqualTo(40.0);
        verify(shoppingCartDao, times(1)).updateCartTotals(cartId);
    }
}

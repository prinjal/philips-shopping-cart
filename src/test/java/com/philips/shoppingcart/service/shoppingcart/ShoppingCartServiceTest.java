package com.philips.shoppingcart.service.shoppingcart;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.dto.shoppingcart.ResponseShoppingCartDto;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.impl.ShoppingCartServiceImpl;
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
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShoppingCartServiceTest extends AbstractTestContainer {

    @Mock
    private ShoppingCartDao shoppingCartDao;
    @Mock
    private ItemDao itemDao;

    @Mock
    private ProductDao productDao;

    private ShoppingCartServiceImpl shoppingCartService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        shoppingCartService = new ShoppingCartServiceImpl(shoppingCartDao,itemDao,productDao);  // Manually instantiate and inject mock
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
        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));

        // When
        ResponseShoppingCartDto retrievedCart = shoppingCartService.getShoppingCartById(cartId);

        // Then
        assertThat(retrievedCart.getId()).isEqualTo(cart.getId());
        verify(shoppingCartDao, times(1)).getShoppingCartById(cartId);
    }

    @Test
    void addItemToCart() {
        // Given
        Long cartId = 1L;
        Long productId = 1L;
        Long itemId = 1L;
        int quantity = 2;
        Product product = new Product(productId, "Product", 100.0);
        Item item = new Item();
        item.setId(itemId);
        item.setProduct(product);
        item.setQuantity(quantity);

        ShoppingCart cart = new ShoppingCart();
        cart.setId(itemId);

        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));
        when(shoppingCartDao.saveShoppingCart(cart)).thenReturn(cart);

        // When
        RequestItemDto requestItemDto = new RequestItemDto(productId, quantity);
        ResponseShoppingCartDto updatedCart = shoppingCartService.addItemToCart(cartId, requestItemDto);

        ResponseProductDto responseProductDto=new ResponseProductDto(productId, product.getName(), product.getPrice());

        // Then
        assertThat(updatedCart.getItems())
                .extracting(ResponseItemDto::getProduct, ResponseItemDto::getQuantity)
                .containsExactly(tuple(responseProductDto, quantity));
        verify(shoppingCartDao, times(2)).getShoppingCartById(cartId);
        verify(productDao, times(1)).getProductById(productId);
        verify(shoppingCartDao, times(2)).saveShoppingCart(cart);
    }

    @Test
    void updateItemInCart() {
        // Given
        Long cartId = 1L;
        Long itemId = 1L;
        Long productId = 1L;
        Item item = new Item();
        item.setId(itemId);
        Product product = new Product(productId, "Product", 100.0);
        item.setProduct(product);
        item.setQuantity(5);

        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        cart.getItems().add(item);
        item.setShoppingCart(cart);

        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));
        when(shoppingCartDao.saveShoppingCart(cart)).thenReturn(cart);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        ResponseShoppingCartDto updatedCart = shoppingCartService.updateItemInCart(cartId, itemId, new RequestItemDto(
                productId, item.getQuantity()
        ));

        // Then
        assertThat(updatedCart.getItems()).anyMatch(i -> i.getQuantity() == item.getQuantity());
        verify(shoppingCartDao, times(2)).getShoppingCartById(cartId);
        verify(shoppingCartDao, times(2)).saveShoppingCart(cart);
        verify(productDao, times(1)).getProductById(productId); // Ensure the productDao is called
    }

    @Test
    void removeItemFromCart() {
        // Given
        Long cartId = 1L;
        Long itemId = 1L;
        Item item = new Item();
        item.setId(itemId);
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        cart.getItems().add(item);

        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));
        when(shoppingCartDao.saveShoppingCart(cart)).thenReturn(cart);

        // When
        ResponseShoppingCartDto updatedCart = shoppingCartService.removeItemFromCart(cartId, itemId);

        // Then
        assertThat(updatedCart.getItems()).noneMatch(i -> i.getId().equals(itemId));
        verify(shoppingCartDao, times(2)).getShoppingCartById(cartId);
        verify(shoppingCartDao, times(2)).saveShoppingCart(cart);
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
        cart.getItems().add(item1);
        cart.getItems().add(item2);
        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));

        // When
        List<Item> retrievedItems = shoppingCartService.getAllItemsInCart(cartId);

        // Then
        assertThat(retrievedItems).hasSize(2);
        assertThat(retrievedItems).containsExactlyInAnyOrder(item1, item2);
        verify(shoppingCartDao, times(1)).getShoppingCartById(cartId);
    }

    @Test
    void updateCartTotals() {
        // Given
        Long cartId = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(cartId);
        Item item1 = new Item();
        item1.setQuantity(2);
        item1.setProduct(new Product(1L, "Product1", 10.0));
        Item item2 = new Item();
        item2.setQuantity(1);
        item2.setProduct(new Product(2L, "Product2", 20.0));
        cart.getItems().addAll(Arrays.asList(item1, item2));
        item1.setShoppingCart(cart);
        item2.setShoppingCart(cart);
        when(shoppingCartDao.getShoppingCartById(cartId)).thenReturn(Optional.of(cart));
        when(shoppingCartDao.saveShoppingCart(cart)).thenReturn(cart);

        // When
        ResponseShoppingCartDto updatedCart = shoppingCartService.updateCartTotals(cartId);

        // Then
        assertThat(updatedCart.getTotalItems()).isEqualTo(3);
        assertThat(updatedCart.getTotalPrice()).isEqualTo(40.0);
        verify(shoppingCartDao, times(1)).getShoppingCartById(cartId);
        verify(shoppingCartDao, times(1)).saveShoppingCart(cart);
    }
}

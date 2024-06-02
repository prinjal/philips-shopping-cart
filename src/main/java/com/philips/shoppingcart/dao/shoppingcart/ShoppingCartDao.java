package com.philips.shoppingcart.dao.shoppingcart;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart addItemToCart(Long cartId, Item item);

    ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item);

    ShoppingCart removeItemFromCart(Long cartId, Long itemId);

    List<Item> getAllItemsInCart(Long cartId);

    ShoppingCart updateCartTotals(Long shoppingCart);
}

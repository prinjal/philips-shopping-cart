package com.philips.shoppingcart.dao.shoppingcart;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    ShoppingCart getShoppingCartById(Long id);

    Item addItemToCart(Long cartId, Item item);

    Item updateItemInCart(Long cartId, Long itemId, Item item);

    void removeItemFromCart(Long cartId, Long itemId);

    List<Item> getAllItemsInCart(Long cartId);

    void updateCartTotals(ShoppingCart shoppingCart);
}

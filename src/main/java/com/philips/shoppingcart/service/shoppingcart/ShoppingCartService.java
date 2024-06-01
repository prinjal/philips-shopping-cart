package com.philips.shoppingcart.service.shoppingcart;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(Long id);

    Item addItemToCart(Long cartId, Item item);

    Item updateItemInCart(Long cartId, Long itemId, Item item);

    void removeItemFromCart(Long cartId, Long itemId);

    List<Item> getAllItemsInCart(Long cartId);

    void updateCartTotals(ShoppingCart shoppingCart);

}

package com.philips.shoppingcart.service.shoppingcart;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart addItemToCart(Long cartId, Item item);

    ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item);

    ShoppingCart removeItemFromCart(Long cartId, Long itemId);

    List<Item> getAllItemsInCart(Long cartId);

    ShoppingCart updateCartTotals(ShoppingCart shoppingCart);

}

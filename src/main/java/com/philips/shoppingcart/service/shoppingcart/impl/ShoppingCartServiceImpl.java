package com.philips.shoppingcart.service.shoppingcart.impl;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return null;
    }

    @Override
    public ShoppingCart addItemToCart(Long cartId, Item item) {
        return null;
    }

    @Override
    public ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item) {
        return null;
    }

    @Override
    public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
        return null;
    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        return null;
    }

    @Override
    public ShoppingCart updateCartTotals(ShoppingCart shoppingCart) {

        return null;
    }
}

package com.philips.shoppingcart.dao.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ShoppingCartJpaDao")
@Primary
public class ShoppingCartJpaDataAccessImpl implements ShoppingCartDao {
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
    public ShoppingCart updateCartTotals(Long shoppingCart) {

        return null;
    }
}

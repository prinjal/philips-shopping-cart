package com.philips.shoppingcart.dao.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ShoppingCartJdbcDao")
public class ShoppingCartJdbcDataAccessImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return null;
    }

    @Override
    public Item addItemToCart(Long cartId, Item item) {
        return null;
    }

    @Override
    public Item updateItemInCart(Long cartId, Long itemId, Item item) {
        return null;
    }

    @Override
    public void removeItemFromCart(Long cartId, Long itemId) {

    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        return null;
    }

    @Override
    public void updateCartTotals(ShoppingCart shoppingCart) {

    }
}

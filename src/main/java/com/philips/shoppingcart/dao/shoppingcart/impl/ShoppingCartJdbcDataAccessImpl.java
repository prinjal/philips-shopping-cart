package com.philips.shoppingcart.dao.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ShoppingCartJdbcDao")
public class ShoppingCartJdbcDataAccessImpl implements ShoppingCartDao {
    @Override
    public List<ShoppingCart> getAllShoppingCarts() {
        return null;
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return null;
    }

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return null;
    }
}

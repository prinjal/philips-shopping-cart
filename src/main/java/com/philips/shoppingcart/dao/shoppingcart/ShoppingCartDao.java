package com.philips.shoppingcart.dao.shoppingcart;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartDao {

    List<ShoppingCart> getAllShoppingCarts();
    Optional<ShoppingCart> getShoppingCartById(Long id);

    ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
}

package com.philips.shoppingcart.service.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartDao.getShoppingCartById(id);
    }

    @Override
    public ShoppingCart addItemToCart(Long cartId, Item item) {
        return shoppingCartDao.addItemToCart(cartId,item);
    }

    @Override
    public ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item) {
        return updateItemInCart(cartId,itemId,item);
    }

    @Override
    public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
        return shoppingCartDao.removeItemFromCart(cartId,itemId);
    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        return shoppingCartDao.getAllItemsInCart(cartId);
    }

    @Override
    public ShoppingCart updateCartTotals(Long shoppingCartId) {

        return shoppingCartDao.updateCartTotals(shoppingCartId);
    }
}

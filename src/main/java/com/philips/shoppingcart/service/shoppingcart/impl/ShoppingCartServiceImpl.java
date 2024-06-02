package com.philips.shoppingcart.service.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
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
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartDao.getShoppingCartById(id)
                .orElseThrow(() -> new ResourceNotFound("Shopping Cart not found"));
    }

    @Override
    public ShoppingCart addItemToCart(Long cartId, Item item) {
        ShoppingCart shoppingCart = getShoppingCartById(cartId);
        shoppingCart.getItems().add(item);
        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item) {
        ShoppingCart shoppingCart = getShoppingCartById(cartId);
        shoppingCart.getItems().stream()
                .filter(existingItem -> existingItem.getId().equals(itemId))
                .findFirst()
                .ifPresent(existingItem -> {
                    existingItem.setQuantity(item.getQuantity());
                    existingItem.setProduct(item.getProduct());
                });
        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
        ShoppingCart shoppingCart = getShoppingCartById(cartId);
        shoppingCart.getItems().removeIf(item -> item.getId().equals(itemId));
        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        return getShoppingCartById(cartId).getItems();
    }

    @Override
    public ShoppingCart updateCartTotals(Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCartById(shoppingCartId);
        double totalPrice = shoppingCart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
        int totalItems = shoppingCart.getItems().stream()
                .mapToInt(Item::getQuantity)
                .sum();
        shoppingCart.setTotalPrice(totalPrice);
        shoppingCart.setTotalItems(totalItems);
        return shoppingCartDao.saveShoppingCart(shoppingCart);
    }
}

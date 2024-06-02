package com.philips.shoppingcart.dao.shoppingcart.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.repository.ShoppingCartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ShoppingCartJpaDao")
@Primary
@RequiredArgsConstructor
public class ShoppingCartJpaDataAccessImpl implements ShoppingCartDao {

    private final ShoppingCartJpaRepository shoppingCartJpaRepository;

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartJpaRepository.findById(id);
    }

    @Override
    public ShoppingCart addItemToCart(Long cartId, Item item) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartJpaRepository.findById(cartId);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();
            List<Item> itemsInCart = existingShoppingCart.getItems();
            itemsInCart.add(item);
            existingShoppingCart.setItems(itemsInCart);
            return shoppingCartJpaRepository.save(existingShoppingCart);
        } else {
            throw new ResourceNotFound("Shopping Cart not found");
        }
    }

    @Override
    public ShoppingCart updateItemInCart(Long cartId, Long itemId, Item item) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartJpaRepository.findById(cartId);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();
            List<Item> itemsInCart = existingShoppingCart.getItems();
            for (Item existingItem : itemsInCart) {
                if (existingItem.getId().equals(itemId)) {
                    existingItem.setQuantity(item.getQuantity());
                    existingItem.setProduct(item.getProduct());
                }
            }
            return shoppingCartJpaRepository.save(existingShoppingCart);
        } else {
            throw new ResourceNotFound("Shopping Cart not found");
        }
    }

    @Override
    public ShoppingCart removeItemFromCart(Long cartId, Long itemId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartJpaRepository.findById(cartId);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();
            List<Item> itemsInCart = existingShoppingCart.getItems();
            itemsInCart.removeIf(item -> item.getId().equals(itemId));
            existingShoppingCart.setItems(itemsInCart);
            return shoppingCartJpaRepository.save(existingShoppingCart);
        } else {
            throw new ResourceNotFound("Shopping Cart not found");
        }
    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartJpaRepository.findById(cartId);
        if (optionalShoppingCart.isPresent()) {
            return optionalShoppingCart.get().getItems();
        } else {
            throw new ResourceNotFound("Shopping Cart not found");
        }
    }

    @Override
    public ShoppingCart updateCartTotals(Long shoppingCartId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartJpaRepository.findById(shoppingCartId);
        if (optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();
            double totalPrice = existingShoppingCart.getItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                    .sum();
            int totalItems = existingShoppingCart.getItems().stream()
                    .mapToInt(Item::getQuantity)
                    .sum();
            existingShoppingCart.setTotalPrice(totalPrice);
            existingShoppingCart.setTotalItems(totalItems);
            return shoppingCartJpaRepository.save(existingShoppingCart);
        } else {
            throw new ResourceNotFound("Shopping Cart not found");
        }
    }
}

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
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartJpaRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartById(Long id) {
        return shoppingCartJpaRepository.findById(id);
    }


    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartJpaRepository.save(shoppingCart);
    }
}

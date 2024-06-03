package com.philips.shoppingcart.service.shoppingcart;

import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.shoppingcart.RequestShoppingCartDto;
import com.philips.shoppingcart.dto.shoppingcart.ResponseShoppingCartDto;
import com.philips.shoppingcart.model.Item;

import java.util.List;

public interface ShoppingCartService {
    ResponseShoppingCartDto createShoppingCart(RequestShoppingCartDto requestShoppingCartDto);

    ResponseShoppingCartDto getShoppingCartById(Long id);

    ResponseShoppingCartDto addItemToCart(Long cartId, RequestItemDto item);

    ResponseShoppingCartDto updateItemInCart(Long cartId, Long itemId, RequestItemDto item);

    ResponseShoppingCartDto removeItemFromCart(Long cartId, Long itemId);

    List<Item> getAllItemsInCart(Long cartId);

    ResponseShoppingCartDto updateCartTotals(Long shoppingCartId);

}

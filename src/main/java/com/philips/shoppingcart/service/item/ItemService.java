package com.philips.shoppingcart.service.item;

import com.philips.shoppingcart.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> getItemById(Long id);

    List<Item> getItems();
}

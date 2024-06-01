package com.philips.shoppingcart.service.item;

import com.philips.shoppingcart.model.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(Long id);

    List<Item> getItems();
}

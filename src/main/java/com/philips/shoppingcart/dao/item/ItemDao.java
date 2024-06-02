package com.philips.shoppingcart.dao.item;

import com.philips.shoppingcart.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    Optional<Item> getItemById(Long id);

    List<Item> getItems();
}

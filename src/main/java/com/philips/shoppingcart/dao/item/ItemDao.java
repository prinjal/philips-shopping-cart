package com.philips.shoppingcart.dao.item;

import com.philips.shoppingcart.model.Item;

import java.util.List;

public interface ItemDao {
    Item getItemById(Long id);

    List<Item> getItems();
}

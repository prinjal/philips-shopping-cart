package com.philips.shoppingcart.dao.item.impl;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ItemJdbcDao")
public class ItemJdbcDataAccessImpl implements ItemDao {
    @Override
    public Optional<Item> getItemById(Long id) {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }
}

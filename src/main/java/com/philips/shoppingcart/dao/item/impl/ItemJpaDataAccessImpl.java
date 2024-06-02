package com.philips.shoppingcart.dao.item.impl;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.model.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component("ItemJpaDao")
@Primary
public class ItemJpaDataAccessImpl implements ItemDao {
    @Override
    public Item getItemById(Long id) {
        return null;
    }

    @Override
    public List<Item> getItems() {
        return null;
    }
}

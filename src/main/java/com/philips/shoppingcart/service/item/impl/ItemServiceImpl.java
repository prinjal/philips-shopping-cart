package com.philips.shoppingcart.service.item.impl;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemDao.getItemById(id);
    }

    @Override
    public List<Item> getItems() {
        return itemDao.getItems();
    }
}

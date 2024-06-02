package com.philips.shoppingcart.dao.item.impl;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.repository.ItemJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("ItemJpaDao")
@Primary
@RequiredArgsConstructor
public class ItemJpaDataAccessImpl implements ItemDao {


    private final ItemJpaRepository itemJpaRepository;

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemJpaRepository.findById(id);
    }

    @Override
    public List<Item> getItems() {
        return itemJpaRepository.findAll();
    }
}

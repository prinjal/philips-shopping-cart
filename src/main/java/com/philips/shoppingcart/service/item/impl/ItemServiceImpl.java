package com.philips.shoppingcart.service.item.impl;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public Optional<ResponseItemDto> getItemById(Long id) {
        Optional<Item> item = itemDao.getItemById(id);
        if (item.isPresent()) {
            Item existingItem = item.get();
            Product existingProduct = existingItem.getProduct();
            return Optional.of(new ResponseItemDto(existingItem.getId(),
                    new ResponseProductDto(existingItem.getId(),
                            existingProduct.getName(),
                            existingProduct.getPrice()), existingItem.getQuantity()));
        }
        else{
            throw new ResourceNotFound("Item not present");
        }
    }

    @Override
    public List<ResponseItemDto> getItems() {
        List<Item> items = itemDao.getItems();
        return items.stream()
                .map(item -> new ResponseItemDto(
                        item.getId(),
                        new ResponseProductDto(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPrice()),
                        item.getQuantity()))
                .collect(Collectors.toList());
    }
}

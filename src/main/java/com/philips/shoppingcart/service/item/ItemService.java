package com.philips.shoppingcart.service.item;

import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<ResponseItemDto> getItemById(Long id);

    List<ResponseItemDto> getItems();
}

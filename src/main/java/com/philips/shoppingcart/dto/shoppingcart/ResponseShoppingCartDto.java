package com.philips.shoppingcart.dto.shoppingcart;


import com.philips.shoppingcart.dto.item.ResponseItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseShoppingCartDto {
    private Long id;
    private List<ResponseItemDto> items;
    private double totalPrice;
    private int totalItems;
}

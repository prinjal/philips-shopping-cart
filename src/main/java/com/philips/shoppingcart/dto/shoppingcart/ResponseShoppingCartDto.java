package com.philips.shoppingcart.dto.shoppingcart;


import com.philips.shoppingcart.dto.item.ResponseItemDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseShoppingCartDto {
    private Long id;
    private List<ResponseItemDto> items;
    private double totalPrice;
    private int totalItems;
}

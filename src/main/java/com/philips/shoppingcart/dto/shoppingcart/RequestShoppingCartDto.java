package com.philips.shoppingcart.dto.shoppingcart;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestShoppingCartDto {
    private List<Long> itemIds;
}

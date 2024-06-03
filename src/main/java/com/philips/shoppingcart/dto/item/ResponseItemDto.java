package com.philips.shoppingcart.dto.item;

import com.philips.shoppingcart.dto.product.ResponseProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseItemDto {
    private Long id;
    private ResponseProductDto productId;
    private int quantity;
}
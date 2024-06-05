package com.philips.shoppingcart.dto.item;

import com.philips.shoppingcart.dto.product.ResponseProductDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseItemDto {
    private Long id;
    private ResponseProductDto product;
    private int quantity;
    private Long shoppngCartId;
}
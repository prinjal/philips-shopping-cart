package com.philips.shoppingcart.dto.product;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ResponseProductDto {
    private Long id;
    private String name;
    private double price;
}

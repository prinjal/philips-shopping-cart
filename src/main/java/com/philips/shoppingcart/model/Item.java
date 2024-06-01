package com.philips.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Item {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false
    )

    private Product product;

    @Column(
            nullable = false
    )
    private int quantity;

    @ManyToOne()
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;

}

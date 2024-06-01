package com.philips.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "ITEM"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_id_seq",
            sequenceName = "item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "item_id_seq"
    )
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
    @JoinColumn(name = "shopping_cart_id",nullable = false)
    private ShoppingCart shoppingCart;

}

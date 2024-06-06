package com.philips.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Table(
        name = "ITEMS"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"product","shoppingCart"})
public class Item {
    @Id
    @SequenceGenerator(
            name = "items_id_seq",
            sequenceName = "items_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "items_id_seq"
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

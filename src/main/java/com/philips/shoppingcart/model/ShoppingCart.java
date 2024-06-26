package com.philips.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "SHOPPING_CARTS"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = "items")
public class ShoppingCart {
    @Id
    @SequenceGenerator(
            name = "shopping_carts_id_seq",
            sequenceName = "shopping_carts_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "shopping_carts_id_seq"
    )
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @ReadOnlyProperty
    private double totalPrice;

    @ReadOnlyProperty
    private int totalItems;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;
}

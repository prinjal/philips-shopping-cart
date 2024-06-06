package com.philips.shoppingcart.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Table(
        name = "PRODUCTS"
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Product {
    @Id
    @SequenceGenerator(
            name = "products_id_seq",
            sequenceName = "products_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "products_id_seq"
    )
    private Long id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            nullable = false,
            unique = true
    )
    private double price;

}

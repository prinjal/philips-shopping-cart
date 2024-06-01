package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaInterface extends JpaRepository<Item,Integer> {
}

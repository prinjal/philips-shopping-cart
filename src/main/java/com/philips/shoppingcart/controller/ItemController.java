package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController extends AbstractRestController{
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return  null;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return  null;
    }
}

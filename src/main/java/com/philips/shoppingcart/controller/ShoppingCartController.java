package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController extends AbstractRestController{
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        return  null;
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<Item> addItemToCart(@PathVariable Long id, @RequestBody Item item) {
        return  null;
    }

    @PutMapping("/{id}/items/{itemId}")
    public ResponseEntity<Item> updateItemInCart(@PathVariable Long id, @PathVariable Long itemId, @RequestBody Item item) {
        return  null;
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long id, @PathVariable Long itemId) {
        return  null;
    }
}

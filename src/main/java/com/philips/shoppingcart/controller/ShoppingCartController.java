package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.shoppingcart.RequestShoppingCartDto;
import com.philips.shoppingcart.dto.shoppingcart.ResponseShoppingCartDto;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.ShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartController {


    private final ShoppingCartService shoppingCartService;

    @PostMapping("")
    public ResponseEntity<ResponseShoppingCartDto> createShoppingCart(@RequestBody RequestShoppingCartDto shoppingCartDto) {
        ResponseShoppingCartDto shoppingCart = shoppingCartService.createShoppingCart(shoppingCartDto);
        return ResponseEntity.ok(shoppingCart);
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseShoppingCartDto>> getShoppingCarts() {
        List<ResponseShoppingCartDto> shoppingCart = shoppingCartService.getAllShoppingCarts();
        return ResponseEntity.ok(shoppingCart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseShoppingCartDto> getShoppingCartById(@PathVariable Long id) {
        ResponseShoppingCartDto shoppingCart = shoppingCartService.getShoppingCartById(id);
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<ResponseShoppingCartDto> addItemToCart(@PathVariable Long id, @RequestBody RequestItemDto item) {
        ResponseShoppingCartDto responseCart = shoppingCartService.addItemToCart(id, item);
        return ResponseEntity.status(201).body(responseCart);
    }

    @PutMapping("/{id}/items/{itemId}")
    public ResponseEntity<ResponseShoppingCartDto> updateItemInCart(@PathVariable Long id, @PathVariable Long itemId, @RequestBody RequestItemDto item) {
        ResponseShoppingCartDto updatedCart = shoppingCartService.updateItemInCart(id, itemId, item);
        return ResponseEntity.ok(updatedCart);
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable Long id, @PathVariable Long itemId) {
        shoppingCartService.removeItemFromCart(id, itemId);
        return ResponseEntity.noContent().build();
    }
}

package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    @GetMapping("/{id}")
    public ResponseEntity<ResponseItemDto> getItemById(@PathVariable Long id) {
        Optional<ResponseItemDto> item = itemService.getItemById(id);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseItemDto>> getAllItems() {
        List<ResponseItemDto> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }
}

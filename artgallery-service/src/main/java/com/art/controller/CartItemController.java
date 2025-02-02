package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.CartItemDto;
import com.art.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @PostMapping
    public ResponseEntity<String> addCartItem(@RequestBody CartItemDto cartItemDTO) {
        return ResponseEntity.ok(cartItemService.addCartItem(cartItemDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItemDetails(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.getCartItemDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCartItem(@PathVariable Long id, @RequestBody CartItemDto cartItemDTO) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, cartItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.deleteCartItem(id));
    }
}
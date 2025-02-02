package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.CartDto;
import com.art.pojos.CartEntity;
import com.art.pojos.CartItem;
import com.art.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PostMapping
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDTO) {
        return ResponseEntity.ok(cartService.addCart(cartDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartDetails(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@PathVariable Long id, @RequestBody CartDto cartDTO) {
        return ResponseEntity.ok(cartService.updateCart(id, cartDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.deleteCart(id));
    }
    
    @PutMapping("/user/{userId}/artwork/{artworkId}/quantity/{quantity}")
    public List<CartItem> addToCart(@PathVariable Long userId, @PathVariable Long artworkId, @PathVariable Integer quantity) {
        return cartService.addToCart(userId, artworkId, quantity);
    }
    
    @DeleteMapping("/user/{userId}/artwork/{artworkId}")
    public List<CartItem> removeFromCart(@PathVariable Long userId, @PathVariable Long artworkId) {
        return cartService.removeFromCart(userId, artworkId);
    }
    
}
package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.ShippingDto;
import com.art.service.ShippingService;

import java.util.List;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping
    public ResponseEntity<List<ShippingDto>> getAllShippings() {
        return ResponseEntity.ok(shippingService.getAllShippings());
    }

    @PostMapping
    public ResponseEntity<String> addShipping(@RequestBody ShippingDto shippingDTO) {
        return ResponseEntity.ok(shippingService.addShipping(shippingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingDto> getShippingDetails(@PathVariable Long id) {
        return ResponseEntity.ok(shippingService.getShippingDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateShipping(@PathVariable Long id, @RequestBody ShippingDto shippingDTO) {
        return ResponseEntity.ok(shippingService.updateShipping(id, shippingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShipping(@PathVariable Long id) {
        return ResponseEntity.ok(shippingService.deleteShipping(id));
    }
}
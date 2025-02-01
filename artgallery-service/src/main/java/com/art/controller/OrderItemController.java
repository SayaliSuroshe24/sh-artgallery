package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.OrderItemDto;
import com.art.service.OrderItemService;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDto>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @PostMapping
    public ResponseEntity<String> addOrderItem(@RequestBody OrderItemDto orderItemDTO) {
        return ResponseEntity.ok(orderItemService.addOrderItem(orderItemDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDto> getOrderItemDetails(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDto orderItemDTO) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.deleteOrderItem(id));
    }
}
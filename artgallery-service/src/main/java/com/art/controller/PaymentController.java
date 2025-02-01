package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.PaymentDto;
import com.art.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PaymentDto paymentDTO) {
        return ResponseEntity.ok(paymentService.addPayment(paymentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentDetails(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable Long id, @RequestBody PaymentDto paymentDTO) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
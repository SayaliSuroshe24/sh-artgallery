package com.art.service;

import java.util.List;

import com.art.dto.PaymentDto;

public interface PaymentService {
	   List<PaymentDto> getAllPayments();
	    String addPayment(PaymentDto paymentDTO);
	    String deletePayment(Long paymentId);
	    PaymentDto getPaymentDetails(Long paymentId);
	    String updatePayment(Long paymentId, PaymentDto paymentDTO);
}

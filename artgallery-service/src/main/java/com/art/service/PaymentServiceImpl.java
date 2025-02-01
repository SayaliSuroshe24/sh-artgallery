package com.art.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.PaymentDao;
import com.art.dto.PaymentDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Payment;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(payment -> mapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addPayment(PaymentDto paymentDTO) {
        Payment payment = mapper.map(paymentDTO, Payment.class);
        Payment savedPayment = paymentRepository.save(payment);
        return "New Payment added with id " + savedPayment.getId();
    }

    @Override
    public String deletePayment(Long paymentId) {
        if (paymentRepository.existsById(paymentId)) {
            paymentRepository.deleteById(paymentId);
            return "Payment deleted";
        }
        throw new ResourceNotFoundException("Invalid Payment ID!");
    }

    @Override
    public PaymentDto getPaymentDetails(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Payment ID"));
        return mapper.map(payment, PaymentDto.class);
    }

    @Override
    public String updatePayment(Long paymentId, PaymentDto paymentDTO) {
        if (paymentRepository.existsById(paymentId)) {
            Payment payment = mapper.map(paymentDTO, Payment.class);
            payment.setId(paymentId);
            paymentRepository.save(payment);
            return "Update success";
        }
        throw new ResourceNotFoundException("Payment doesn't exist!");
    }
}



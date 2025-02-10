package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Payment;

public interface PaymentDao extends JpaRepository<Payment, Long>{
	  Payment findByRazorpayOrderId(String razorpayOrderId);

}

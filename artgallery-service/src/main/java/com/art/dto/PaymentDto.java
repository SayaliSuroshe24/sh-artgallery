package com.art.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
	private Long id;
    private Long orderId; // FK
    private Date paymentDate;
    private double amount;
    private String paymentMethod;
    private String status;
}

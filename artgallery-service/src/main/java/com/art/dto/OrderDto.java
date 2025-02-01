package com.art.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	  private Long orderId;
	    private Long custId; // FK
	    private Date orderDate;
	    private double totalAmount;
	    private String orderStatus;
}

package com.art.dto;

import java.util.Date;
import java.util.List;

import com.art.pojos.OrderItem;

import lombok.Data;

@Data
public class OrderDto {
	  private Long orderId;
	    private Long custId; // FK
	    private Date orderDate;
	    private double totalAmount;
	    private String orderStatus;
	    private List<OrderItemDto> orderItemDto; 
	    private List<OrderItem> orderItems;
}

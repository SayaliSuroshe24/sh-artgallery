package com.art.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	 private Long orderItemId;
	    private Long orderId; // FK
	    private Long artworkId; // FK
	    private int quantity;
	    private double price;
}

package com.art.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDto {
	 private Long id;
	    private Long orderId; // FK
	    private Date date;
	    private String address;
	    private String status;
}

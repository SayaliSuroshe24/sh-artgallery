package com.art.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
	private Long userId;
    private String name;
    private String email;
    private String password;
    private String phoneNo;
    private String shippingNo;
    private String role; // ADMIN or CUSTOMER
    private Date dateCreated;
}

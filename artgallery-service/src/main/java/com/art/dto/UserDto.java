package com.art.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

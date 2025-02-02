package com.art.dto;

import java.util.Date;

import com.art.pojos.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class CartDto {
	private Long cartId;
	private Long custId; // FK
}

package com.art.dto;

import com.art.pojos.Artwork;
import com.art.pojos.CartEntity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CartItemDto {
	 private Long cartItemId;
	    private Long cartId; // FK
	    private Long artworkId; // FK
	    private int quantity;
}

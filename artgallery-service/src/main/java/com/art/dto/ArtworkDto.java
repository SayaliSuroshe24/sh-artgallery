package com.art.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtworkDto {
	 private Long artId;
	    private String title;
	    private Long artistId; // FK
	    private double price;
	    private boolean availability;
	    private String imageUrl;
	    private Long categoryId; // FK
}

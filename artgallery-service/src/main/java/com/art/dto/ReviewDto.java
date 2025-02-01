package com.art.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
	private Long reviewId;
    private Long userId; // FK
    private Long artworkId; // FK
    private int rating;
    private String comment;
    private Date reviewDate;
}

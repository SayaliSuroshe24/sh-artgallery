package com.art.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDto {
	    private Long artistId;
	    private String name;
	    private String biography;
	    private String country;
	    private String contactNo;
	    private String email;
}

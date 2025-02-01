package com.art.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Artwork {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long artId;
	    private String title;
	    @ManyToOne
	    @JoinColumn(name = "artist_id")
	    private Artist artist;
	    private double price;
	    private boolean availability;
	    private String imageUrl;
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
		public void setArtId(Long artId2) {
			// TODO Auto-generated method stub
			
		}
}

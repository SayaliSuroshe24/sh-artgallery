package com.art.pojos;

import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//
//@Data
public class Artwork {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long artId;
	    private String title;
	    @ManyToOne(fetch=FetchType.LAZY)
	    @JoinColumn(name = "artist_id")
	    private Artist artist;
	    private double price;
	    private boolean availability;
	    @Lob
	    private byte[] image;
	    //private String imageUrl;
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
		public Long getArtId() {
			return artId;
		}
		public void setArtId(Long artId) {
			this.artId = artId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Artist getArtist() {
			return artist;
		}
		public void setArtist(Artist artist) {
			this.artist = artist;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public boolean isAvailability() {
			return availability;
		}
		public void setAvailability(boolean availability) {
			this.availability = availability;
		}

		/*
		 * public String getImageUrl() { return imageUrl; } public void
		 * setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
		 */
		public Category getCategory() {
			return category;
		}
		public void setCategory(Category category) {
			this.category = category;
		}
		
		
		 public byte[] getImage() {
				return image;
			}
		public void setImage(byte[] image) {
				this.image = image;
			}
	
			
			
			
		public Artwork() {
			super();
		}
		public Artwork(Long artId, String title, Artist artist, double price, boolean availability, byte[] image,
				Category category) {
			super();
			this.artId = artId;
			this.title = title;
			this.artist = artist;
			this.price = price;
			this.availability = availability;
			this.image = image;
			this.category = category;
		}
		@Override
		public String toString() {
			return "Artwork [artId=" + artId + ", title=" + title + ", artist=" + artist + ", price=" + price
					+ ", availability=" + availability + ", image=" + Arrays.toString(image) + ", category=" + category
					+ "]";
		}
		/*
		 * @Override public String toString() { return "Artwork [artId=" + artId +
		 * ", title=" + title + ", artist=" + artist + ", price=" + price +
		 * ", availability=" + availability + ", imageUrl=" + imageUrl + ", category=" +
		 * category + "]"; }
		 */
		
		
		
		
	    
		
		
}

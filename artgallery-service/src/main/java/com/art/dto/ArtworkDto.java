package com.art.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//@Data
public class ArtworkDto {
	 private Long artId;
	    private String title;
	    private Long artistId; // FK
	    private double price;
	    private boolean availability;
//	    private String imageUrl;
	   // private MultipartFile imageUrl;
	    private byte[] image;
	    private Long categoryId; // FK
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
		public Long getArtistId() {
			return artistId;
		}
		public void setArtistId(Long artistId) {
			this.artistId = artistId;
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
//		public String getImageUrl() {
//			return imageUrl;
//		}
//		public void setImageUrl(String imageUrl) {
//			this.imageUrl = imageUrl;
//		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		
		
		/*
		 * public MultipartFile getImageUrl() { return imageUrl; } public void
		 * setImageUrl(MultipartFile imageUrl) { this.imageUrl = imageUrl; }
		 */
//		public ArtworkDto(Long artId, String title, Long artistId, double price, boolean availability,
//				Long categoryId) {
//			super();
//			this.artId = artId;
//			this.title = title;
//			this.artistId = artistId;
//			this.price = price;
//			this.availability = availability;
///        	this.imageUrl = imageUrl;
//			this.categoryId = categoryId;
//		}
		
		
		
		
		
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
		public ArtworkDto(){
			
		}
		public ArtworkDto(Long artId, String title, Long artistId, double price, boolean availability,
				/* MultipartFile imageUrl, */ Long categoryId) {
			super();
			this.artId = artId;
			this.title = title;
			this.artistId = artistId;
			this.price = price;
			this.availability = availability;
			//this.imageUrl = imageUrl;
			this.categoryId = categoryId;
		}
//		@Override
//		public String toString() {
//			return "ArtworkDto [artId=" + artId + ", title=" + title + ", artistId=" + artistId + ", price=" + price
//					+ ", availability=" + availability  + ", categoryId=" + categoryId + "]";
//		}
//	    
		@Override
		public String toString() {
			return "ArtworkDto [artId=" + artId + ", title=" + title + ", artistId=" + artistId + ", price=" + price
					+ ", availability=" + availability
					+ /* ", imageUrl=" + imageUrl + */ ", categoryId=" + categoryId + "]";
		}
		
		
		
		
}

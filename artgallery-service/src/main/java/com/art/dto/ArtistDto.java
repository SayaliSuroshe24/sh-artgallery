package com.art.dto;

import lombok.Data;

@Data
public class ArtistDto {
	    private Long artistId;
	    private String name;
	    private String biography;
	    private String country;
	    private String contactNo;
	    private String email;
		public Long getArtistId() {
			return artistId;
		}
		public void setArtistId(Long artistId) {
			this.artistId = artistId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBiography() {
			return biography;
		}
		public void setBiography(String biography) {
			this.biography = biography;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getContactNo() {
			return contactNo;
		}
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public ArtistDto(Long artistId, String name, String biography, String country, String contactNo, String email) {
			super();
			this.artistId = artistId;
			this.name = name;
			this.biography = biography;
			this.country = country;
			this.contactNo = contactNo;
			this.email = email;
		}
		public ArtistDto() {
			
		}
		@Override
		public String toString() {
			return "ArtistDto [artistId=" + artistId + ", name=" + name + ", biography=" + biography + ", country="
					+ country + ", contactNo=" + contactNo + ", email=" + email + "]";
		}
		
		
	    
}

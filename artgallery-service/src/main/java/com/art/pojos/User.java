package com.art.pojos;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class User {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;
	    private String name;
	    private String email;
	    private String password;
	    private String phoneNo;
	    private String shippingNo;
	    @Enumerated(EnumType.STRING)
	    private Role role;
	    private Date dateCreated;
	    

	    public Long getUserId() {
			return userId;
		}


		public void setUserId(Long userId) {
			this.userId = userId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getPhoneNo() {
			return phoneNo;
		}


		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}


		public String getShippingNo() {
			return shippingNo;
		}


		public void setShippingNo(String shippingNo) {
			this.shippingNo = shippingNo;
		}


		public Role getRole() {
			return role;
		}


		public void setRole(Role role) {
			this.role = role;
		}


		public Date getDateCreated() {
			return dateCreated;
		}


		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}


		public enum Role {
	        ADMIN, CUSTOMER
	    }
}

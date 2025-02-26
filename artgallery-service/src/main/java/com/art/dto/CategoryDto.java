package com.art.dto;

import lombok.Data;

@Data
public class CategoryDto {
	 private Long categoryId;
	    private String categoryName;
		public Long getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public CategoryDto(Long categoryId, String categoryName) {
			super();
			this.categoryId = categoryId;
			this.categoryName = categoryName;
		}
	    public CategoryDto() {
	    	
	    }
}

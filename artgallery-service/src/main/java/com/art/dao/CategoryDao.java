package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Category;

public interface CategoryDao  extends JpaRepository<Category, Long> {

}

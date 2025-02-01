package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Review;

public interface ReviewDao extends JpaRepository<Review, Long> {

}

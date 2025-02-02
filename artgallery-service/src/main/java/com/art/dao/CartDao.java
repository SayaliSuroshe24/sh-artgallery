package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Artist;
import com.art.pojos.CartEntity;

public interface CartDao extends JpaRepository<CartEntity, Long>{
	
	CartEntity findByCustomer_UserId(Long userId);

}

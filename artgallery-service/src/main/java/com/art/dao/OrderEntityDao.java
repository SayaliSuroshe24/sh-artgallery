package com.art.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.OrderEntity;

public interface OrderEntityDao extends JpaRepository<OrderEntity, Long> {
	List<OrderEntity> findByCustomer_UserId(Long userId);   
}

package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.OrderEntity;

public interface OrderEntityDao extends JpaRepository<OrderEntity, Long> {
    
}

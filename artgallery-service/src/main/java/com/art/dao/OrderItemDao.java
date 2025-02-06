package com.art.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long>  {
	
	List<OrderItem> findByOrder_OrderId(Long userId);   

}

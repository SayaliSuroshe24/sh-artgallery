package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Long>  {

}

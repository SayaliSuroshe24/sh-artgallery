package com.art.service;

import java.util.List;

import com.art.dto.OrderDto;

public interface OrderService {
	 List<OrderDto> getAllOrders();
	    String addOrder(OrderDto orderDTO);
	    String deleteOrder(Long orderId);
	    OrderDto getOrderDetails(Long orderId);
	    String updateOrder(Long orderId, OrderDto orderDTO);
}

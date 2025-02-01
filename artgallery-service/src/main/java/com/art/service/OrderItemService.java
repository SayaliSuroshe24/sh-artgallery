package com.art.service;

import java.util.List;

import com.art.dto.OrderItemDto;

public interface OrderItemService {
	List<OrderItemDto> getAllOrderItems();
    String addOrderItem(OrderItemDto orderItemDTO);
    String deleteOrderItem(Long orderItemId);
    OrderItemDto getOrderItemDetails(Long orderItemId);
    String updateOrderItem(Long orderItemId, OrderItemDto orderItemDTO);
}

package com.art.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.OrderItemDao;
import com.art.dto.OrderItemDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.OrderItem;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(orderItem -> mapper.map(orderItem, OrderItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addOrderItem(OrderItemDto orderItemDTO) {
        OrderItem orderItem = mapper.map(orderItemDTO, OrderItem.class);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return "New Order Item added with id " + savedOrderItem.getOrderItemId();
    }

    @Override
    public String deleteOrderItem(Long orderItemId) {
        if (orderItemRepository.existsById(orderItemId)) {
            orderItemRepository.deleteById(orderItemId);
            return "Order Item deleted";
        }
        throw new ResourceNotFoundException("Invalid Order Item ID!");
    }

    @Override
    public OrderItemDto getOrderItemDetails(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Order Item ID"));
        return mapper.map(orderItem, OrderItemDto.class);
    }

    @Override
    public String updateOrderItem(Long orderItemId, OrderItemDto orderItemDTO) {
        if (orderItemRepository.existsById(orderItemId)) {
            OrderItem orderItem = mapper.map(orderItemDTO, OrderItem.class);
            orderItem.setOrderItemId(orderItemId);
            orderItemRepository.save(orderItem);
            return "Update success";
        }
        throw new ResourceNotFoundException("Order Item doesn't exist!");
    }
}
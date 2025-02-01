package com.art.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.OrderEntityDao;
import com.art.dto.OrderDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.OrderEntity;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderEntityDao orderRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addOrder(OrderDto orderDTO) {
        OrderEntity order = mapper.map(orderDTO, OrderEntity.class);
        OrderEntity savedOrder = orderRepository.save(order);
        return "New Order added with id " + savedOrder.getOrderId();
    }

    @Override
    public String deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return "Order deleted";
        }
        throw new ResourceNotFoundException("Invalid Order ID!");
    }

    @Override
    public OrderDto getOrderDetails(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Order ID"));
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public String updateOrder(Long orderId, OrderDto orderDTO) {
        if (orderRepository.existsById(orderId)) {
            OrderEntity order = mapper.map(orderDTO, OrderEntity.class);
            order.setOrderId(orderId);
            orderRepository.save(order);
            return "Update success";
        }
        throw new ResourceNotFoundException("Order doesn't exist!");
    }
}
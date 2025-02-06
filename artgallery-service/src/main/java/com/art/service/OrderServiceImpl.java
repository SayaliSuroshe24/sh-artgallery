package com.art.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.ArtworkDao;
import com.art.dao.OrderEntityDao;
import com.art.dao.OrderItemDao;
import com.art.dao.UserDao;
import com.art.dto.OrderDto;
import com.art.dto.OrderItemDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.OrderEntity;
import com.art.pojos.OrderItem;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderEntityDao orderRepository;
    
    @Autowired
    private OrderItemDao orderItemDao;
    
    @Autowired
    private ArtworkDao artworkDao;
    
    @Autowired
    private UserDao userDao;

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
        order.setCustomer(userDao.findById(orderDTO.getCustId()).get());
        OrderEntity savedOrder = orderRepository.save(order);
        for (OrderItemDto itemDto : orderDTO.getOrderItemDto()) {
        	OrderItem item = new OrderItem();
        	item.setOrder(savedOrder);
        	item.setPrice(itemDto.getPrice());
        	item.setQuantity(1);
        	item.setArtwork(artworkDao.findById(itemDto.getArtworkId()).get());
        	orderItemDao.save(item);
		}
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

	@Override
	public List<OrderDto> getUserOrders(Long userId) {
		List<OrderEntity> orderEntities = orderRepository.findByCustomer_UserId(userId);
		List<OrderDto> orderDtos = new LinkedList<>();
		for (OrderEntity orderEntity : orderEntities) {
			List<OrderItem> orderItems = orderItemDao.findByOrder_OrderId(orderEntity.getOrderId());
			OrderDto orderDto = new OrderDto();
			orderDto.setOrderItems(orderItems);
			orderDto.setCustId(userId);
			orderDto.setOrderDate(orderEntity.getOrderDate());
			orderDto.setOrderId(orderEntity.getOrderId());
			orderDto.setOrderStatus(orderEntity.getOrderStatus());
			orderDto.setTotalAmount(orderEntity.getTotalAmount());
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}
}
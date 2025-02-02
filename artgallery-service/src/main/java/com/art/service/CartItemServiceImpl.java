package com.art.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.CartItemDao;
import com.art.dto.CartItemDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.CartItem;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDao cartItemRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<CartItemDto> getAllCartItems() {
        return cartItemRepository.findAll().stream()
                .map(cartItem -> mapper.map(cartItem, CartItemDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addCartItem(CartItemDto cartItemDTO) {
        CartItem cartItem = mapper.map(cartItemDTO, CartItem.class);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return "New Cart Item added with id " + savedCartItem.getCartItemId();
    }

    @Override
    public String deleteCartItem(Long cartItemId) {
        if (cartItemRepository.existsById(cartItemId)) {
            cartItemRepository.deleteById(cartItemId);
            return "Cart Item deleted";
        }
        throw new ResourceNotFoundException("Invalid Cart Item ID!");
    }

    @Override
    public CartItemDto getCartItemDetails(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Cart Item ID"));
        return mapper.map(cartItem, CartItemDto.class);
    }

    @Override
    public String updateCartItem(Long cartItemId, CartItemDto cartItemDTO) {
        if (cartItemRepository.existsById(cartItemId)) {
            CartItem cartItem = mapper.map(cartItemDTO, CartItem.class);
            cartItem.setCartItemId(cartItemId);
            cartItemRepository.save(cartItem);
            return "Update success";
        }
        throw new ResourceNotFoundException("Cart Item doesn't exist!");
    }
}
package com.art.service;

import java.util.List;

import com.art.dto.CartItemDto;

public interface CartItemService {
	List<CartItemDto> getAllCartItems();
    String addCartItem(CartItemDto cartItemDTO);
    String deleteCartItem(Long cartItemId);
    CartItemDto getCartItemDetails(Long cartItemId);
    String updateCartItem(Long cartItemId, CartItemDto cartItemDTO);
}

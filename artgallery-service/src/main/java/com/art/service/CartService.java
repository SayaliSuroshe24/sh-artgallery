package com.art.service;

import java.util.List;

import com.art.dto.CartDto;
import com.art.pojos.CartItem;

public interface CartService {
	
	 List<CartDto> getAllCarts();
	    String addCart(CartDto cartDTO);
	    String deleteCart(Long cartId);
	    CartDto getCartDetails(Long cartId);
	    String updateCart(Long cartId, CartDto cartDTO);
		List<CartItem> addToCart(Long userId, Long artworkId, int quantity);
		List<CartItem> removeFromCart(Long userId, Long artworkId);
		List<CartItem> getCartListByUser(Long userId);

}

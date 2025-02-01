package com.art.service;

import java.util.List;

import com.art.dto.ShippingDto;

public interface ShippingService {
	 List<ShippingDto> getAllShippings();
	    String addShipping(ShippingDto shippingDTO);
	    String deleteShipping(Long shippingId);
	    ShippingDto getShippingDetails(Long shippingId);
	    String updateShipping(Long shippingId, ShippingDto shippingDTO);
}

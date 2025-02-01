package com.art.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.ShippingDao;
import com.art.dto.ShippingDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Shipping;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingDao shippingRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ShippingDto> getAllShippings() {
        return shippingRepository.findAll().stream()
                .map(shipping -> mapper.map(shipping, ShippingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addShipping(ShippingDto shippingDTO) {
        Shipping shipping = mapper.map(shippingDTO, Shipping.class);
        Shipping savedShipping = shippingRepository.save(shipping);
        return "New Shipping added with id " + savedShipping.getId();
    }

    @Override
    public String deleteShipping(Long shippingId) {
        if (shippingRepository.existsById(shippingId)) {
            shippingRepository.deleteById(shippingId);
            return "Shipping deleted";
        }
        throw new ResourceNotFoundException("Invalid Shipping ID!");
    }

    @Override
    public ShippingDto getShippingDetails(Long shippingId) {
        Shipping shipping = shippingRepository.findById(shippingId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Shipping ID"));
        return mapper.map(shipping, ShippingDto.class);
    }

    @Override
    public String updateShipping(Long shippingId, ShippingDto shippingDTO) {
        if (shippingRepository.existsById(shippingId)) {
            Shipping shipping = mapper.map(shippingDTO, Shipping.class);
            shipping.setId(shippingId);
            shippingRepository.save(shipping);
            return "Update success";
        }
        throw new ResourceNotFoundException("Shipping doesn't exist!");
    }
}
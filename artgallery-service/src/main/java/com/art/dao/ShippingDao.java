package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Shipping;

public interface ShippingDao extends JpaRepository<Shipping, Long> {

}

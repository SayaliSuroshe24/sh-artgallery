package com.art.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Artist;
import com.art.pojos.CartEntity;
import com.art.pojos.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long>{

	List<CartItem> findByCart(CartEntity cart);

	Optional<CartItem> findByCartAndArtwork_ArtId(CartEntity cart, Long artworkId);

}

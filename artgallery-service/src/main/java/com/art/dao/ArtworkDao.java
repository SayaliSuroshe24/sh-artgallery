package com.art.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.dto.ArtworkDto;
import com.art.pojos.Artwork;

public interface ArtworkDao extends JpaRepository<Artwork, Long> {

	List<Artwork> findByCategoryCategoryId(Long categoryId);    
}

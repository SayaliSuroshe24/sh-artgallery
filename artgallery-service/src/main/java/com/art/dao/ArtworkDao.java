package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Artwork;

public interface ArtworkDao extends JpaRepository<Artwork, Long> {
    

}

package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long>{

}

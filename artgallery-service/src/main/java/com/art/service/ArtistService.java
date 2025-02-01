package com.art.service;

import java.util.List;

import com.art.dto.ArtistDto;

public interface ArtistService {
	 List<ArtistDto> getAllArtists();
	    String addArtist(ArtistDto artistDto);
	    String deleteArtist(Long artistId);
	    ArtistDto getArtistDetails(Long artistId);
	    String updateArtist(Long artistId, ArtistDto artistDto);
}

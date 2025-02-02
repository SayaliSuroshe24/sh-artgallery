package com.art.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.art.dto.ArtworkDto;

public interface ArtworkService {
	    List<ArtworkDto> getAllArtworks();
	   String addArtwork(ArtworkDto artworkDTO,MultipartFile file) throws IOException;
	   // String addArtwork(ArtworkDto artworkDto) throws IOException;
	    String deleteArtwork(Long artId);
	    ArtworkDto getArtworkDetails(Long artId);
	    String updateArtwork(Long artId, ArtworkDto artworkDTO, MultipartFile image);
		//String addArtwork(String title, Long artistId, Long categoryId, double price, boolean availability,
		//		String filePath);
		List<ArtworkDto> getArtworksByCategory(long categoryId);
}

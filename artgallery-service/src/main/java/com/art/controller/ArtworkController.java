package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.art.dto.ArtworkDto;
import com.art.service.ArtworkService;
import com.art.service.FileStorageService;

import io.swagger.v3.oas.annotations.Parameter;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Enable CORS for this controller
@RequestMapping("/api/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping 
    public ResponseEntity<List<ArtworkDto>> getAllArtworks() {
        return ResponseEntity.ok(artworkService.getAllArtworks());
    }
    
    @GetMapping("/category/{categoryId}") 
    public ResponseEntity<List<ArtworkDto>> getArtworksByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(artworkService.getArtworksByCategory(categoryId));
    }
    
    
	@PostMapping(value = "/upload", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> uploadEmpAndImage(@RequestPart MultipartFile image, @RequestPart ArtworkDto artworkDto)
			throws IOException {
		System.out.println("in upload emp details n image " + artworkDto + " " + image);
		return ResponseEntity.status(HttpStatus.CREATED).body(artworkService.addArtwork(artworkDto, image));
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDto> getArtworkDetails(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.getArtworkDetails(id));
    }

    @PutMapping(value = "/{id}", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateArtwork(@PathVariable Long id, @RequestPart MultipartFile image, @RequestPart ArtworkDto artworkDto)
			throws IOException {
    	if(artworkDto.getArtistId() == null || artworkDto.getCategoryId() == null || artworkDto.getTitle() == null) {
    		return ResponseEntity.badRequest().body("Invalid Request");
    	}
		System.out.println("in upload emp details n image " + artworkDto + " " + image);
		return ResponseEntity.status(HttpStatus.OK).body(artworkService.updateArtwork(id, artworkDto, image));
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtwork(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.deleteArtwork(id));
    }
}
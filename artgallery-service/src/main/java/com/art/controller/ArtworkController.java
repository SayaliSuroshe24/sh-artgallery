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
    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping 
    public ResponseEntity<List<ArtworkDto>> getAllArtworks() {
        return ResponseEntity.ok(artworkService.getAllArtworks());
    }
    
    /*
     *   @Parameter(description = "Artwork details") @ModelAttribute ArtworkDto artworkDTO,  // Bind DTO fields using @ModelAttribute
            @Parameter(description = "Image file to upload") @RequestParam("image") MultipartFile file) {
        
     */
    
	@PostMapping(value = "/upload", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE/*
												 * , MediaType.APPLICATION_JSON_VALUE
												 */ })
	public ResponseEntity<?> uploadEmpAndImage(@RequestPart MultipartFile image, @RequestPart ArtworkDto artworkDto)
			throws IOException {
		System.out.println("in upload emp details n image " + artworkDto + " " + image);
		return ResponseEntity.status(HttpStatus.CREATED).body(artworkService.addArtwork(artworkDto, image));
	}

	/*
	 * @PostMapping(value = "/upload", consumes =
	 * MediaType.MULTIPART_FORM_DATA_VALUE) public ResponseEntity<String>
	 * addArtwork( @Parameter(description =
	 * "Artwork details") @RequestParam("artworkDTO") ArtworkDto artworkDTO,
	 * 
	 * @Parameter(description = "Image file to upload") @RequestParam("image")
	 * MultipartFile image) { //return
	 * ResponseEntity.ok(artworkService.addArtwork(artworkDTO,file)); try {
	 * 
	 * System.out.println("Received Artist ID: " + artworkDTO.getArtistId());
	 * System.out.println("Received Category ID: " + artworkDTO.getCategoryId());
	 * System.out.println("Received file: " + image.getOriginalFilename());
	 * 
	 * 
	 * 
	 * artworkService.addArtwork(artworkDTO, image); // Pass DTO and MultipartFile
	 * to service return new ResponseEntity<>("Artwork added successfully!",
	 * HttpStatus.CREATED); } catch (NullPointerException e) { return new
	 * ResponseEntity<>("Null pointer exception occurred.",
	 * HttpStatus.INTERNAL_SERVER_ERROR); } catch (IOException e) { return new
	 * ResponseEntity<>("File handling error.", HttpStatus.INTERNAL_SERVER_ERROR); }
	 * catch (Exception e) { return new ResponseEntity<>("Failed to add artwork.",
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 */


   
	/*
	 * @PostMapping("/upload") public ResponseEntity<String> uploadArtwork(
	 * 
	 * @RequestBody ArtworkDto artworkDto) {
	 * 
	 * try { // Handle file upload and store image String filePath =
	 * fileStorageService.saveFile(artworkDto.getImageUrl());
	 * 
	 * // Save the artwork metadata along with the image URL //
	 * artworkService.addArtwork(artworkDto // artworkDto.getTitle(), //
	 * artworkDto.getArtistId(), // artworkDto.getCategoryId(), //
	 * artworkDto.getPrice(), // artworkDto.isAvailability(), // filePath
	 * 
	 * 
	 * artworkService.addArtwork(artworkDto);
	 * 
	 * // );
	 * 
	 * return ResponseEntity.ok("Artwork uploaded successfully!"); } catch
	 * (Exception e) { return
	 * ResponseEntity.status(500).body("Error uploading artwork."); } }
	 */

    
    
    
    
    
    
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDto> getArtworkDetails(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.getArtworkDetails(id));
    }

	/*
	 * @PutMapping("/{id}") public ResponseEntity<String>
	 * updateArtwork(@PathVariable Long id, @RequestBody ArtworkDto artworkDTO) {
	 * if(artworkDTO.getArtistId() == null || artworkDTO.getCategoryId() == null ||
	 * artworkDTO.getTitle() == null) { return
	 * ResponseEntity.badRequest().body("Invalid Request"); } return
	 * ResponseEntity.ok(artworkService.updateArtwork(id, artworkDTO)); }
	 */
    
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
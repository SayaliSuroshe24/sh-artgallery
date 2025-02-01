package com.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.art.dto.ArtworkDto;
import com.art.service.ArtworkService;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping
    public ResponseEntity<List<ArtworkDto>> getAllArtworks() {
        return ResponseEntity.ok(artworkService.getAllArtworks());
    }

    @PostMapping
    public ResponseEntity<String> addArtwork(@RequestBody ArtworkDto artworkDTO) {
        return ResponseEntity.ok(artworkService.addArtwork(artworkDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtworkDto> getArtworkDetails(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.getArtworkDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArtwork(@PathVariable Long id, @RequestBody ArtworkDto artworkDTO) {
        return ResponseEntity.ok(artworkService.updateArtwork(id, artworkDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtwork(@PathVariable Long id) {
        return ResponseEntity.ok(artworkService.deleteArtwork(id));
    }
}
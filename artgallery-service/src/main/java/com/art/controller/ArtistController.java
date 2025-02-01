package com.art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dto.ArtistDto;
import com.art.service.ArtistService;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @PostMapping
    public ResponseEntity<String> addArtist(@RequestBody ArtistDto artistDTO) {
        return ResponseEntity.ok(artistService.addArtist(artistDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtistDetails(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDTO) {
        return ResponseEntity.ok(artistService.updateArtist(id, artistDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.deleteArtist(id));
    }
}

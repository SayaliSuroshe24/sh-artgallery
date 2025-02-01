package com.art.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.ArtworkDao;
import com.art.dto.ArtworkDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Artwork;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkDao artworkRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ArtworkDto>getAllArtworks() {
    	 return artworkRepository.findAll().stream()
    	                .map(user -> mapper.map(user, ArtworkDto.class))
    	                .collect(Collectors.toList());
    }
    
    @Override
    public String addArtwork(ArtworkDto artworkDTO) {
        Artwork artwork = mapper.map(artworkDTO, Artwork.class);
        Artwork savedArtwork = artworkRepository.save(artwork);
        return "New Artwork added with id " + savedArtwork.getArtId();
    }

    @Override
    public String deleteArtwork(Long artId) {
        if (artworkRepository.existsById(artId)) {
            artworkRepository.deleteById(artId);
            return "Artwork deleted";
        }
        throw new ResourceNotFoundException("Invalid Artwork ID!");
    }

    @Override
    public ArtworkDto getArtworkDetails(Long artId) {
        Artwork artwork = artworkRepository.findById(artId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Artwork ID"));
        return mapper.map(artwork, ArtworkDto.class);
    }

    @Override
    public String updateArtwork(Long artId, ArtworkDto artworkDTO) {
        if (artworkRepository.existsById(artId)) {
            Artwork artwork = mapper.map(artworkDTO, Artwork.class);
            artwork.setArtId(artId);
            artworkRepository.save(artwork);
            return "Update success";
        }
        throw new ResourceNotFoundException("Artwork doesn't exist!");
    }
}

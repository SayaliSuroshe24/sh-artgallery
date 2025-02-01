package com.art.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.art.dao.ArtistDao;
import com.art.dto.ArtistDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Artist;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDao artistRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ArtistDto> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(artist -> mapper.map(artist, ArtistDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addArtist(ArtistDto artistDTO) {
        Artist artist = mapper.map(artistDTO, Artist.class);
        Artist savedArtist = artistRepository.save(artist);
        return "New Artist added with id " + savedArtist.getArtistId();
    }

    @Override
    public String deleteArtist(Long artistId) {
        if (artistRepository.existsById(artistId)) {
            artistRepository.deleteById(artistId);
            return "Artist deleted";
        }
        throw new ResourceNotFoundException("Invalid Artist ID!");
    }

    @Override
    public ArtistDto getArtistDetails(Long artistId) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Artist ID"));
        return mapper.map(artist, ArtistDto.class);
    }

    @Override
    public String updateArtist(Long artistId, ArtistDto artistDTO) {
        if (artistRepository.existsById(artistId)) {
            Artist artist = mapper.map(artistDTO, Artist.class);
            artist.setArtistId(artistId);
            artistRepository.save(artist);
            return "Update success";
        }
        throw new ResourceNotFoundException("Artist doesn't exist!");
    }
}
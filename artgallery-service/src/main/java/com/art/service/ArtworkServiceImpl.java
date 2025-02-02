package com.art.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.art.dao.ArtistDao;
import com.art.dao.ArtworkDao;
import com.art.dao.CategoryDao;
import com.art.dto.ArtworkDto;
import com.art.exception.ResourceNotFoundException;
import com.art.pojos.Artist;
import com.art.pojos.Artwork;
import com.art.pojos.Category;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArtworkServiceImpl implements ArtworkService {

    @Autowired
    private ArtworkDao artworkRepository;
    @Autowired
    private ArtistDao artistRepository;
    @Autowired
    private CategoryDao categoryRepository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public List<ArtworkDto> getAllArtworks() {
//    	 return artworkRepository.findAll().stream()
//    	                .map(user -> mapper.map(user, ArtworkDto.class))
//    	                .collect(Collectors.toList());
//    	 
//    	 /*
//    	 / * return artworks.stream()
//                .map(artwork -> new ArtworkDTO(
//                        artwork.getId(),
//                        artwork.getArtist() != null ? artwork.getArtist().getId() : null,
//                        artwork.getCategory() != null ? artwork.getCategory().getId() : null
//                ))
//                .collect(Collectors.toList());
//    	  */
//    	 
    	return artworkRepository.findAll().stream()
    	        .map(artwork -> {
    	            ArtworkDto artworkDto = mapper.map(artwork, ArtworkDto.class);
    	            // Set artistId
    	            artworkDto.setArtistId(artwork.getArtist().getArtistId()); // Assuming getArtist() returns the artist entity and getId() retrieves the ID
    	            // Set categoryId
    	            artworkDto.setCategoryId(artwork.getCategory().getCategoryId()); // Assuming getCategory() returns the category entity and getId() retrieves the ID
    	            
    	            return artworkDto;
    	        })
    	        .collect(Collectors.toList()); 
    	 
    }
    
    
	/*
	 * public String addArtwork(ArtworkDto artworkDto) throws IOException { // Fetch
	 * the artist and category from their respective repositories Artist artist =
	 * artistRepository.findById(artworkDto.getArtistId()) .orElseThrow(() -> new
	 * RuntimeException("Artist not found"));
	 * 
	 * Category category = categoryRepository.findById(artworkDto.getCategoryId())
	 * .orElseThrow(() -> new RuntimeException("Category not found"));
	 * 
	 * // Save the file (image) and get the file path or URL String filePath =
	 * fileStorageService.saveFile(artworkDto.getImageUrl());
	 * 
	 * // Create a new Artwork object Artwork artwork = new Artwork();
	 * artwork.setTitle(artworkDto.getTitle()); artwork.setArtist(artist);
	 * artwork.setPrice(artworkDto.getPrice());
	 * artwork.setAvailability(artworkDto.isAvailability());
	 * artwork.setImageUrl(filePath); // Store the file path/URL in the artwork
	 * entity artwork.setCategory(category);
	 * 
	 * // Save the artwork object to the database artworkRepository.save(artwork);
	 * 
	 * return "Artwork uploaded successfully!"; }
	 */	

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//        public void saveArtworkWithFilePath(String title, Long artistId, Long categoryId, String fileName) {
//            Artwork artwork = new Artwork();
//            artwork.setTitle(title);
//            artwork.setArtistId(artistId);
//            artwork.setCategoryId(categoryId);
//            artwork.setImagePath(fileName); // Save the file path
//
//            artworkRepository.save(artwork);
//        }
//    }
    	
    @Override
    public String addArtwork(ArtworkDto artworkDTO,MultipartFile file) throws IOException {  
   	System.out.println(artworkDTO.getArtistId());
   	Long artistId=artworkDTO.getArtistId();
   	Long categoryId=artworkDTO.getCategoryId();
   	System.out.println(artworkDTO.getCategoryId());
    	
    	 Artist artist = artistRepository.findById(artistId)
                 .orElseThrow(() -> new ResourceNotFoundException("Invalid Artist ID"));
   	System.out.println(artist);
    	//System.out.println(artist);
   	 Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    	 System.out.println(category);
   	 
        Artwork artwork = mapper.map(artworkDTO, Artwork.class);
       artwork.setImage(file.getBytes());
       artwork.setArtist(artist);
       artwork.setCategory(category);
        System.out.println(artwork);
        System.out.println(artwork.toString());
        Artwork savedArtwork = artworkRepository.save(artwork);
        return "New Artwork added with id " + savedArtwork.getArtId();
        
        /* Artist artist = artistRepository.findById(artworkDTO.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        Category category = categoryRepository.findById(artworkDTO.getCategoryId())
               .orElseThrow(() -> new RuntimeException("Category not found"));

        // Create a new Artwork entity and set the values
        Artwork artwork = new Artwork();
        artwork.setArtist(artist);
        artwork.setCategory(category);
        artwork.setName(artworkDTO.getName());

        // Save the artwork entity
        return artworkRepository.save(artwork);
         * 
        */
        
        
        
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
       Long artistId= artwork.getArtist().getArtistId();
       Long categoryId= artwork.getCategory().getCategoryId();
//       System.out.println(artistId);
//       System.out.println(categoryId);
//       ArtworkDto artDto=new ArtworkDto();
//       artDto.setArtistId(artistId);
//       artDto.setCategoryId(categoryId);
//       System.out.println(artDto.toString());
       ArtworkDto artDto=mapper.map(artwork, ArtworkDto.class);
       artDto.setArtistId(artistId);
       artDto.setCategoryId(categoryId);
       
       System.out.println(artDto.toString());
       
        return artDto;
    }

    @Override
	public String updateArtwork(Long artId, ArtworkDto artworkDTO, MultipartFile image) {
		if (artworkRepository.existsById(artId)) {
			Artwork artwork = mapper.map(artworkDTO, Artwork.class);
			Artist artist = artistRepository.findById(artworkDTO.getArtistId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid Artist ID"));
			System.out.println(artist);
			// System.out.println(artist);
			Category category = categoryRepository.findById(artworkDTO.getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category not found"));

			artwork.setArtist(artist);
			artwork.setCategory(category);
			try {
				artwork.setImage(image.getBytes());
			} catch (IOException e) {
				System.out.println(e);
			}
			System.out.println(category);
			artwork.setArtId(artId);
			artworkRepository.save(artwork);
			return "Update success";
		}
		throw new ResourceNotFoundException("Artwork doesn't exist!");
	}


	

	
}

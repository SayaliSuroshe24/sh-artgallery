package com.art.pojos;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artistId;
    private String name;
    private String biography;
    private String country;
    private String contactNo;
    private String email;


//    @OneToMany(mappedBy = "artist")
//    private List<Artwork> artworks;
}

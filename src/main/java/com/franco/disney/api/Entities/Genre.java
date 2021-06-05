package com.franco.disney.api.Entities;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Data
public class Genre {
    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "genre_sequence")
    @Column(name = "genre_id",unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "genre",targetEntity = Movie.class,cascade =
            {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("genre")
    private Set<Movie> movies = new HashSet<>();

    public Genre() {
    }

    public Genre(String name, String image, Set<Movie> movies) {
        this.name = name;
        this.image = image;
        this.movies = movies;
    }

    public Genre(Long id, String name, String image, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.movies = movies;
    }
}

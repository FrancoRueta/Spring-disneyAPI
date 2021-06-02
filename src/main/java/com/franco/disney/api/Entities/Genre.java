package com.franco.disney.api.Entities;


import lombok.Data;

import javax.persistence.*;
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
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence")
    private Long id;
    private String name;
    private String image;

    @OneToMany(mappedBy = "genre")
    private Set<Movie> movies;

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

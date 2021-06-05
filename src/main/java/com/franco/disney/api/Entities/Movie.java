package com.franco.disney.api.Entities;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;


@Table(name = "movies")
@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Movie implements Comparable<Movie> {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "movie_sequence")
    @Column(name = "movie_id",unique = true)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "date_creation")
    private LocalDate dateCreation;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},
    targetEntity = Celebrity.class)
    @JoinTable(name = "celebrity_movie",
            joinColumns = @JoinColumn(name = "movie_id",referencedColumnName = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "celebrity_id",referencedColumnName = "celebrity_id"))
    @EqualsAndHashCode.Exclude
    private Set<Celebrity> celebrities = new HashSet<>();


    @ManyToOne(targetEntity = Genre.class, cascade =
            {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie() {
    }

    public Movie(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public Movie(String image, String title, LocalDate dateCreation, Set<Celebrity> celebrities, Genre genre) {
        this.image = image;
        this.title = title;
        this.dateCreation = dateCreation;
        this.celebrities = celebrities;
        this.genre = genre;
    }

    public Movie(Long id, String image, String title, LocalDate dateCreation, Set<Celebrity> celebrities, Genre genre) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.dateCreation = dateCreation;
        this.celebrities = celebrities;
        this.genre = genre;
    }

    @Override
    public int compareTo(Movie m) {
        if (getDateCreation() == null || m.getDateCreation() == null) {
            return 0;
        }
        return getDateCreation().compareTo(m.getDateCreation());
    }
}

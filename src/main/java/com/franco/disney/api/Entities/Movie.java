package com.franco.disney.api.Entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


@Table(name = "movies")
@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Movie {
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "movie_sequence")
    @Column(name = "movie_id")
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


    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;


    public Movie(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public Movie(String image, String title, LocalDate dateCreation, Set<Celebrity> celebrities) {
        this.image = image;
        this.title = title;
        this.dateCreation = dateCreation;
        this.celebrities = celebrities;
    }

    public Movie(Long id, String image, String title, LocalDate dateCreation, Set<Celebrity> celebrities) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.dateCreation = dateCreation;
        this.celebrities = celebrities;
    }

    public Movie() {
    }
}

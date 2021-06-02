package com.franco.disney.api.Entities;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "celebrities")
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Celebrity {
    @Id
    @SequenceGenerator(
            name= "celebrity_sequence",
            sequenceName = "celebrity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "celebrity_sequence"
    )
    @Column(name = "celebrity_id")
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "story")
    private String story;


    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},
            mappedBy = "celebrities",targetEntity = Movie.class)
    @EqualsAndHashCode.Exclude
    private Set<Movie> movies = new HashSet<>();



    public Celebrity(String image, String name, Integer age, Integer weight, String story, Set<Movie> movies) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.story = story;
        this.movies = movies;
    }

    public Celebrity(Long id, String image, String name, Integer age, Integer weight, String story, Set<Movie> movies) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.story = story;
        this.movies = movies;
    }

    public Celebrity() {
    }
}

package com.franco.disney.api.Entities.DTOS;

import lombok.Data;
import org.hibernate.mapping.Set;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
public class CelebrityDetailDTO implements Serializable {
    private Long id;
    private String name;
    private String image;
    private Integer age;
    private Integer weight;
    private String story;
    private List<String> movieNames = new ArrayList<>();

}

package com.franco.disney.api.Entities.DTOS;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class MovieDTO implements Serializable {
    private String image;
    private String title;
    private String dateCreation;

    public MovieDTO() {
    }

    public MovieDTO(String image, String title, String dateCreation) {
        this.image = image;
        this.title = title;
        this.dateCreation = dateCreation;
    }
}

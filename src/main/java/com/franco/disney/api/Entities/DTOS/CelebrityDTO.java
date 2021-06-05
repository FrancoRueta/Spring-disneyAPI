package com.franco.disney.api.Entities.DTOS;


import lombok.Data;

import java.io.Serializable;

@Data
public class CelebrityDTO implements Serializable {
    private String name;
    private String image;

    public CelebrityDTO() {
    }

    public CelebrityDTO(String name, String image) {
        this.name = name;
        this.image = image;
    }
}

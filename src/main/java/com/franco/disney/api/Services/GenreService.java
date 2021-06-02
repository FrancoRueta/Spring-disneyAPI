package com.franco.disney.api.Services;


import com.franco.disney.api.Entities.Genre;
import com.franco.disney.api.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    //GET ALL
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    //GET by id
    public Genre getGenre(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() ->
                new IllegalStateException("No existe el genero con id: "+genreId));
    }

    //POST
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    //DELET
    public void deleteGenre(Long genreId) {
        boolean exists = genreRepository.existsById(genreId);
        if (!exists){
            throw new IllegalStateException("No existe el genero con id: "+genreId); }
        genreRepository.deleteById(genreId);
    }

    @Transactional
    public void updateGenre(Long genreId, String name, String image) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() ->
                new IllegalStateException("No existe el genero con id: "+ genreId));
        if (name != null){genre.setName(name);}
        if (image != null){genre.setImage(image);}
    }
}

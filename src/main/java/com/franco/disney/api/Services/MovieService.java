package com.franco.disney.api.Services;


import com.franco.disney.api.Auxiliars.DateTimeAux;
import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.DTOS.MovieDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<MovieDTO> getMovies() {
        /*getMovies: Transforma cada pelicula en la entidad peliculas y las pasa a
        * movieDTO, un transfer object que posee solo los datos necesarios.*/
        List<MovieDTO> dtoList = new ArrayList<>();
        for (Movie m: movieRepository.findAll()){
            DateTimeAux aux = new DateTimeAux();
            MovieDTO newMovie = new MovieDTO();
            if (m.getImage() != null){
            newMovie.setImage(m.getImage()); }
            if (m.getTitle() != null){
            newMovie.setTitle(m.getTitle()); }
            if (m.getDateCreation() != null){
                newMovie.setDateCreation(aux.dtStr(m.getDateCreation())); }
            dtoList.add(newMovie);
        }
        return dtoList;
    }

    //get by id
    public Movie getMovie(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula " + movieId));
    }


    //post
    public void addNewMovie(Movie movie) {
        movieRepository.save(movie);
    }

    //delete
    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists){
            throw new IllegalStateException("No existe la pelicula: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }

    //update
    @Transactional
    public void updateMovie(Long movieId, String image, String title, String dateCreation) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula " + movieId));
        if(image != null){movie.setImage(image);}
        if(title != null){movie.setTitle(title);}
        if(dateCreation != null){
            LocalDate date = LocalDate.parse(dateCreation);
            movie.setDateCreation(date);
        }
    }

    public void addCharacter(Celebrity celebrity, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula " + movieId));
        Set<Celebrity> celebs = movie.getCelebrities();
        celebs.add(celebrity);
        movie.setCelebrities(celebs);
    }
}

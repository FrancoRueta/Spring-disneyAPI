package com.franco.disney.api.Services;


import com.franco.disney.api.Auxiliars.DateTimeAux;
import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.DTOS.MovieDTO;
import com.franco.disney.api.Entities.Genre;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Repositories.CelebrityRepository;
import com.franco.disney.api.Repositories.GenreRepository;
import com.franco.disney.api.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final CelebrityRepository celebrityRepository;
    private final DateTimeAux aux = new DateTimeAux();

    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, CelebrityRepository celebrityRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.celebrityRepository = celebrityRepository;
    }


    public List<MovieDTO> getMovies() {
        /*getMovies: Transforma cada pelicula en la entidad peliculas y las pasa a
         * movieDTO, un transfer object que posee solo los datos necesarios.*/
        List<MovieDTO> dtoList = new ArrayList<>();
        for (Movie m : movieRepository.findAll()) {
            MovieDTO newMovie = new MovieDTO();
            if (m.getImage() != null) { newMovie.setImage(m.getImage()); }
            if (m.getTitle() != null) { newMovie.setTitle(m.getTitle()); }
            if (m.getDateCreation() != null) { newMovie.setDateCreation(aux.dtStr(m.getDateCreation())); }
            dtoList.add(newMovie);
        }
        return dtoList;
    }


    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula " + movieId));
    }


    public Movie getMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula con titulo: " + title));
    }


    public Set<Movie> getMovieByGenreId(Long genreId) {
        return movieRepository.findMovieByGenre_Id(genreId).orElseThrow(() ->
                new IllegalStateException("No existe el genero de id: " + genreId));
    }


    public Set<Movie> getMovieByDate(String date) {
        return movieRepository.findMovieByDateCreation(aux.strDt(date)).orElseThrow(() ->
                new IllegalStateException("No existen peliculas con fecha: " + date));
    }


    public void deleteMovie(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);
        if (!exists) {
            throw new IllegalStateException("No existe la pelicula: " + movieId);
        }
        movieRepository.deleteById(movieId);
    }


    @Transactional
    public void updateMovie(Long movieId, String image, String title, String dateCreation) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalStateException("No existe la pelicula " + movieId));
        if (image != null) {
            movie.setImage(image);
        }
        if (title != null) {
            movie.setTitle(title);
        }
        if (dateCreation != null) {
            LocalDate date = LocalDate.parse(dateCreation);
            movie.setDateCreation(date);
        }
    }


    public void addNewMovie(Movie movie) {
        if (this.doesNotExist(movie)) {
            if (movie.getGenre() != null) {
                this.checkDuplicateGenre(movie);
            }
            if (movie.getCelebrities().size() > 0) {
                Set<Celebrity> newCelebrities = this.checkDuplicateCelebrities(movie);
                movie.setCelebrities(newCelebrities);
            }
            movieRepository.save(movie);
        }
    }


    //auxiliar de addNewMovie
    private boolean doesNotExist(Movie movie) {
        return (!movieRepository.existsByTitle(movie.getTitle()));
    }


    //auxiliar de addNewMovie
    private void checkDuplicateGenre(Movie movie){
        Genre movieGenre = movie.getGenre();
        if (genreRepository.existsByName(movieGenre.getName())) {
            //le asigno el genero ya existente
            movie.setGenre(genreRepository.findByName(movieGenre.getName()));
        }
    }


    //auxiliar de addNewMovie
    private Set<Celebrity> checkDuplicateCelebrities(Movie movie){
        //tomo todas las celebridades y creo una lista vacia:
        Set<Celebrity> celebritiesMovie = movie.getCelebrities();
        Set<Celebrity> newCelebrities = new HashSet<>();
        for (Celebrity celebrity : celebritiesMovie) {
            //si existe en el repo,agrego esta a la lista vacia.
            if (celebrityRepository.existsByName(celebrity.getName())) {
                newCelebrities.add(celebrityRepository.findByName(celebrity.getName()));
            }
            //si no,agrego la original.
            else { newCelebrities.add(celebrity); }
        }
        return newCelebrities;
    }
}

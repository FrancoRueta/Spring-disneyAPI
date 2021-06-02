package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.DTOS.MovieDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    //get all
    @GetMapping
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping(path = "/see/{movieId}")
    public Movie getMovie(@PathVariable("movieId") Long movieId){
        return movieService.getMovie(movieId);
    }

    @PostMapping
    public void addNewMovie(@RequestBody Movie movie){
        movieService.addNewMovie(movie);
    }

    @DeleteMapping(path= "{movieId}")
    public void deleteMovie(@PathVariable("movieId") Long movieId){
        movieService.deleteMovie(movieId);
    }

    @PutMapping(path = "{movieId}")
    public void updateMovie(@PathVariable("movieId") Long movieId,
                            @RequestParam(required = false) String image,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) String dateCreation) {
        movieService.updateMovie(movieId,image,title,dateCreation);
    }

    //DETALLEPELICULA = devuelve los capmos de la peli mas los personajes.
}

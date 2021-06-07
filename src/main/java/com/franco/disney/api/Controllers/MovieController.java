package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.DTOS.MovieDTO;
import com.franco.disney.api.Entities.Genre;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Services.MovieService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieService movieService;


    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    //get all
    @GetMapping(path = "/")
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping
    public Set<Movie> getMovieBy(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String title,
                                 @RequestParam(required = false) Long genre,
                                 @RequestParam(required = false) String date,
                                 @RequestParam(required = false) Integer rate){
        Set<Movie> movieList = new HashSet<>();
        if(id != null){movieList.add(movieService.getMovieById(id));}
        if(title != null){movieList.add(movieService.getMovieByTitle(title));}
        if(genre != null){movieList.addAll(movieService.getMovieByGenreId(genre));}
        if(date != null){movieList.addAll(movieService.getMovieByDate(date));}
        if(rate != null){movieList.addAll(movieService.getMovieByRate(rate));}
        return movieList;
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
                            @RequestParam(required = false) String dateCreation,
                            @RequestParam(required = false) Integer rate) {
        movieService.updateMovie(movieId,image,title,dateCreation,rate);
    }
}

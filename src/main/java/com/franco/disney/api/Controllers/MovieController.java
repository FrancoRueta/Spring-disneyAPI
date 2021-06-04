package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.DTOS.MovieDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    @GetMapping(path = "/")
    public List<MovieDTO> getMovies(){
        return movieService.getMovies();
    }

    @GetMapping
    public List<Movie> getMovieBy(@RequestParam(required = false) Long id,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) Long genre,
                                  @RequestParam(required = false) String date,
                                  @RequestParam(required = false) String order){
        List<Movie> movieList = new ArrayList<>();
        if(id != null){movieList.add(movieService.getMovieById(id));}
        if(title != null){movieList.add(movieService.getMovieByTitle(title));}
        if(genre != null){movieList.addAll(movieService.getMovieByGenreId(genre));}
        if(date != null){movieList.addAll(movieService.getMovieByDate(date));}
        if (order != null){
            if (order.equals("ASC")) { Collections.sort(movieList); }
            else if (order.equals("DESC")){ Collections.reverse(movieList); }
        }
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
                            @RequestParam(required = false) String dateCreation) {
        movieService.updateMovie(movieId,image,title,dateCreation);
    }
}

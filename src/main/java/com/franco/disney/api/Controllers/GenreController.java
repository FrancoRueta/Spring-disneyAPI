package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.Genre;
import com.franco.disney.api.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/genres")
public class GenreController {

    private final GenreService genreService;


    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService; }

    @GetMapping
    public List<Genre> getGenres(){
        return genreService.getGenres();
    }

    @GetMapping(path = "/see/{genreId}")
    public Genre getGenre(@PathVariable("genreId") Long genreId){
        return genreService.getGenre(genreId);
    }

    @PostMapping
    public void addGenre(@RequestBody Genre genre){
        genreService.addGenre(genre);
    }

    @DeleteMapping(path = ("/{genreId}"))
    public void deleteGenre(@PathVariable("genreId") Long genreId){
        genreService.deleteGenre(genreId);}

    @PutMapping(path = "{genreId}")
    public void updateGenre(@PathVariable("genreId") Long genreId,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String image){
        genreService.updateGenre(genreId,name,image);
    }
}

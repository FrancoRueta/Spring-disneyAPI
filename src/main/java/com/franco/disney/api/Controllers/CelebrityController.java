package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.DTOS.CelebrityDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Services.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/characters")
public class CelebrityController {

    private final CelebrityService celebrityService;

    @Autowired
    public CelebrityController(CelebrityService celebrityService) {
        this.celebrityService = celebrityService;
    }

    //get all
    @GetMapping
    public List<CelebrityDTO> getCelebrities() {
        return celebrityService.getCelebrities();
    }

    //get by id
    @GetMapping(path = "/id/")
    public Celebrity getCelebrityById( Long id) {
        return celebrityService.getCelebrityById(id);
    }

    //get by name
    @GetMapping(path = "/name/")
    public Celebrity getCelebrityByName( String name ){
        return celebrityService.getCelebrityByName(name);
    }

    //get by age
    @GetMapping(path = "/age/")
    public List<Celebrity> getCelebrityByAge( Integer age ){
        return celebrityService.getCelebrityByAge(age);
    }

    //get by weight
    @GetMapping(path = "/weight/")
    public List<Celebrity> getCelebrityByWeight( Integer weight ){
        return celebrityService.getCelebrityByWeight(weight);
    }

    //get by movie
    @GetMapping(path = "/movie/")
    public List<Celebrity> getCelebrityByName(Movie movie){
        return celebrityService.getCelebrityByMovie(movie);
    }

    //post
    @PostMapping
    public void addNewCelebrity(@RequestBody Celebrity celebrity) {
        celebrityService.addNewCelebrity(celebrity);
    }


    //delete
    @DeleteMapping(path = {"/{celebrityId}"})
    public void deleteCelebrity(@PathVariable("celebrityId") Long celebrityId) {
        celebrityService.deleteCelebrity(celebrityId);
    }


    //put (funciona como patch)
    @PutMapping(path = "/{celebrityId}")
    public void updateCelebrity(@PathVariable("celebrityId") Long celebrityId,
                                @RequestParam(required = false) String image,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer age,
                                @RequestParam(required = false) Integer weight,
                                @RequestParam(required = false) String story) {
        celebrityService.updateCelebrity(celebrityId, image, name, age, weight, story);
    }

}




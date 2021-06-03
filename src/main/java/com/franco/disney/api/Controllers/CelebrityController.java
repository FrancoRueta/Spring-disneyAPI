package com.franco.disney.api.Controllers;


import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.DTOS.CelebrityDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Services.CelebrityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping(path = "/")
    public List<CelebrityDTO> getCelebrities() {
        return celebrityService.getCelebrities();
    }

    //get by value
    @GetMapping
    public List<Celebrity> getCelebrityBy(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) Integer age,
                                          @RequestParam(required = false) Integer weight,
                                          @RequestParam(required = false) Long movies){
        List<Celebrity> celebrityList = new ArrayList<>();
        if(id != null){celebrityList.add(celebrityService.getCelebrityById(id));}
        if(name != null){celebrityList.add(celebrityService.getCelebrityByName(name));}
        if(age != null){celebrityList.addAll(celebrityService.getCelebrityByAge(age));}
        if(weight != null){celebrityList.addAll(celebrityService.getCelebrityByWeight(weight));}
        if(movies != null){celebrityList.addAll(celebrityService.getCelebrityByMovieId(movies));}
        return celebrityList;
    }

    /*Guardar el personaje que te viene. CHECK
    Recorrer las peliculas que te vinieron.
    Si no existen, crearlas. Si existen, agregarle el personaje mediante un update*/
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




package com.franco.disney.api.Services;


import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.DTOS.CelebrityDTO;
import com.franco.disney.api.Entities.Movie;
import com.franco.disney.api.Repositories.CelebrityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CelebrityService {

    private final CelebrityRepository celebrityRepository;

    @Autowired
    public CelebrityService(CelebrityRepository celebrityRepository) {
        this.celebrityRepository = celebrityRepository;
    }


    //get all
    public List<CelebrityDTO> getCelebrities(){
        List<CelebrityDTO> celebrityDTOList = new ArrayList<>();
        for(Celebrity celebrity: celebrityRepository.findAll()){
            CelebrityDTO newCeleb = new CelebrityDTO();
            newCeleb.setImage(celebrity.getImage());
            newCeleb.setName(celebrity.getName());
            celebrityDTOList.add(newCeleb);
        }
        return celebrityDTOList;
    }

    public List<Celebrity> getCelebritiesFull(){ return celebrityRepository.findAll(); }

    //get by id
    public Celebrity getCelebrityById(Long id) {
        return celebrityRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("No existe el personaje deseado."));
    }

    //get by name
    public Celebrity getCelebrityByName(String name) {
        return celebrityRepository.findCelebrityByName(name).orElseThrow(() ->
                new IllegalStateException("No existe el personaje con nombre"+name));
    }

    //get by weight
    public List<Celebrity> getCelebrityByWeight(Integer weight) {
        return celebrityRepository.findCelebrityByWeight(weight).orElseThrow(() ->
                new IllegalStateException("No existe el personaje con nombre"+weight));
    }

    //get by age
    public List<Celebrity> getCelebrityByAge(Integer age) {
        return celebrityRepository.findCelebrityByAge(age).orElseThrow(() ->
                new IllegalStateException("No existe el personaje con nombre"+age));
    }

    //get by movie
    public List<Celebrity> getCelebrityByMovieId(Long movieId) {
        return celebrityRepository.findCelebrityByMovieId(movieId).orElseThrow(() ->
                new IllegalStateException("No existe el persona con pelicula de id: "+movieId));
    }

    //crear
    public void addNewCelebrity(Celebrity celebrity){
        celebrityRepository.save(celebrity);
    }

    //borrar
    public void deleteCelebrity(Long celebrityId) {
        boolean exists = celebrityRepository.existsById(celebrityId);
        if (!exists){throw new
                IllegalStateException("No existe el personaje con id:" + celebrityId); }
        celebrityRepository.deleteById(celebrityId);
    }

    //update
    @Transactional
    public void updateCelebrity(Long celebrityId, String image, String name, Integer age, Integer weight, String story) {
        Celebrity celebrity = celebrityRepository.findById(celebrityId).orElseThrow(() ->
                new IllegalStateException("No existe el personaje. " + celebrityId));
        if (image != null){celebrity.setImage(image);}
        if (name != null){celebrity.setName(name);}
        if (age != null){celebrity.setAge(age);}
        if (weight != null){celebrity.setWeight(weight);}
        if (story != null ){celebrity.setStory(story);}
    }


}


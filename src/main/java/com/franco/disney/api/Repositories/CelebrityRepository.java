package com.franco.disney.api.Repositories;

import com.franco.disney.api.Entities.Celebrity;
import com.franco.disney.api.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {


    @Query("SELECT c FROM Celebrity c WHERE c.name = ?1")
    Optional<Celebrity> findCelebrityByName(String name);

    @Query("SELECT c FROM Celebrity c WHERE c.weight = ?1")
    Optional<List<Celebrity>> findCelebrityByWeight(Integer weight);

    @Query("SELECT c FROM Celebrity c WHERE c.age = ?1")
    Optional<List<Celebrity>> findCelebrityByAge(Integer age);

    @Query("SELECT c FROM Celebrity c INNER JOIN c.movies m WHERE m.id = ?1")
    Optional<List<Celebrity>> findCelebrityByMovieId(Long movieId);

    boolean existsByName(String name);

    Celebrity findByName(String name);
}

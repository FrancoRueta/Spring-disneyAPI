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

    Optional<Celebrity> findCelebrityByName(String name);

    Optional<List<Celebrity>> findCelebrityByWeight(Integer weight);

    Optional<List<Celebrity>> findCelebrityByAge(Integer age);

    Optional<List<Celebrity>> findCelebrityByMovies_Id(Long movieId);

    boolean existsByName(String name);

    Celebrity findByName(String name);
}

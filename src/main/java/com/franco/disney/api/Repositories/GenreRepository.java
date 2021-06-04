package com.franco.disney.api.Repositories;

import com.franco.disney.api.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsByName(String name);

    Genre findByName(String name);
}

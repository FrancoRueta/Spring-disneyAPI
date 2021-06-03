package com.franco.disney.api.Repositories;

import com.franco.disney.api.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.title = ?1")
    Optional<Movie> findMovieByTitle(String title);

    @Query("SELECT m FROM Movie m INNER JOIN m.genre g WHERE g.id = ?1")
    Optional<List<Movie>> findMovieByGenreId(Long genreId);
}

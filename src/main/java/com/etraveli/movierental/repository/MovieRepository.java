package com.etraveli.movierental.repository;

import com.etraveli.movierental.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findMovieByMovieId(String movieId);
}

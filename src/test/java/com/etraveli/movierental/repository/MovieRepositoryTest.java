package com.etraveli.movierental.repository;

import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie();
        movie.setId(1L);
        movie.setMovieId("F005");
        movie.setTitle("Inception");
        movie.setMovieCode(MovieCode.NEW);
        movie.setGenre("Action");
        movie.setReleaseYear(2010);
        movie = movieRepository.save(movie);
    }

    @Test
    void testFindById() {
        Optional<Movie> found = movieRepository.findById(movie.getId());
        assertTrue(found.isPresent());
        assertEquals(movie.getTitle(), found.get().getTitle());
    }

    @Test
    void testFindByMovieId() {
        Optional<Movie> found = movieRepository.findMovieByMovieId(movie.getMovieId());
        assertTrue(found.isPresent());
        assertEquals(movie.getTitle(), found.get().getTitle());
    }

    @Test
    void testSave() {
        Movie newMovie = new Movie();
        newMovie.setId(2L);
        newMovie.setMovieId("F006");
        newMovie.setTitle("Matrix");
        newMovie.setMovieCode(MovieCode.NEW);
        newMovie.setGenre("Action");
        newMovie.setReleaseYear(1999);
        
        Movie savedMovie = movieRepository.save(newMovie);
        assertNotNull(savedMovie);
        assertEquals("Matrix", savedMovie.getTitle());
    }

    @Test
    void testDelete() {
        movieRepository.deleteById(movie.getId());
        Optional<Movie> deletedMovie = movieRepository.findById(movie.getId());
        assertFalse(deletedMovie.isPresent());
    }

    @Test
    void testFindAll() {
        Iterable<Movie> movies = movieRepository.findAll();
        assertNotNull(movies);
        assertTrue(movies.iterator().hasNext());
    }
}

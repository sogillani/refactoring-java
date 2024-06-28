package com.etraveli.movierental.controller;

import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.dto.MovieListResponse;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  This controller handles CRUD operations for movies in the API.
 *  It provides endpoints for getting all movies, getting a movie by ID, creating a new movie, updating an existing movie, and deleting a movie.
 */
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Gets all movies.
     *
     * @return A {@link MovieListResponse} object with list of movies.
     */
    @GetMapping
    public ResponseEntity<MovieListResponse> getAllMovies() {
        return ResponseEntity.ok(new MovieListResponse(movieService.getAllMovies()));
    }

    /**
     * Gets a movie by ID.
     *
     * @param id The ID of the movie to retrieve.
     * @return A {@link ResponseEntity} containing the movie object or a 404 Not Found status code if not found.
     * @throws MovieNotFoundException if the movie with the provided ID is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    /**
     * Creates a new movie.
     *
     * @param movieDTO The movie object to create.
     * @return A {@link ResponseEntity} containing the created movie object with a 201 Created status code.
     */
    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO movieDTO) {
        LOG.debug("MovieDTO to create movie record: {}", movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.saveMovie(movieDTO));
    }

    /**
     * Updates a movie by ID.
     *
     * @param id The ID of the movie to update.
     * @param movieDTO The updated movie object.
     * @return A {@link ResponseEntity} containing the updated movie object or a 404 Not Found status code if not found.
     * @throws MovieNotFoundException if the movie with the provided ID is not found.
     */
    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        return movieService.updateMovie(id, movieDTO);
    }

    /**
     * Deletes a movie by ID.
     *
     * @param id The ID of the movie to delete.
     * @return A {@link ResponseEntity} with a 204 No Content status code on successful deletion.
     * @throws MovieNotFoundException if the movie with the provided ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}

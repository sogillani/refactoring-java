package com.etraveli.movierental.controller;

import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.dto.MovieListResponse;
import com.etraveli.movierental.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public MovieListResponse getAllMovies() {
        return new MovieListResponse(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        LOG.debug("MovieDTO to create movie record: {}", movieDTO);
        return movieService.saveMovie(movieDTO);
    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDetails) {
        return movieService.updateMovie(id, movieDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

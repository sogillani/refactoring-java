package com.etraveli.movierental.service;


import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.mapper.MovieMapper;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;
    private MovieDTO movieDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        movie = new Movie();
        movie.setId(1L);
        movie.setMovieId("F001");
        movie.setTitle("Test Movie");
        movie.setGenre("Drama");
        movie.setReleaseYear(2020);

        movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        movieDTO.setMovieId("F001");
        movieDTO.setTitle("Test Movie");
        movieDTO.setGenre("Drama");
        movieDTO.setReleaseYear(2020);
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
        when(movieMapper.toMovieDTO(any(Movie.class))).thenReturn(movieDTO);

        List<MovieDTO> movieDTOs = movieService.getAllMovies();

        assertEquals(1, movieDTOs.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testGetMovieById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieMapper.toMovieDTO(any(Movie.class))).thenReturn(movieDTO);

        MovieDTO foundMovie = movieService.getMovieById(1L);

        assertNotNull(foundMovie);
        assertEquals("Test Movie", foundMovie.getTitle());
    }

    @Test
    void testSaveMovie() {
        when(movieMapper.toMovie(any(MovieDTO.class))).thenReturn(movie);
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(movieMapper.toMovieDTO(any(Movie.class))).thenReturn(movieDTO);

        MovieDTO savedMovie = movieService.saveMovie(movieDTO);

        assertNotNull(savedMovie);
        assertEquals("Test Movie", savedMovie.getTitle());
    }

    @Test
    void testDeleteMovie() {
        movieService.deleteMovie(1L);
        verify(movieRepository, times(1)).deleteById(1L);
    }
}

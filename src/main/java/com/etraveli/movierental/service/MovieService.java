package com.etraveli.movierental.service;

import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.exception.MovieNotFoundException;
import com.etraveli.movierental.mapper.MovieMapper;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public Movie findMovieByMovieId(String movieId) {
        return movieRepository.findMovieByMovieId(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie Id not found"));
    }

    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.toMovie(movieDTO);
        return movieMapper.toMovieDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        Movie movie = movieMapper.toMovie(movieDTO);
        movie.setId(id);
        return movieMapper.toMovieDTO(movieRepository.save(movie));
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieMapper::toMovieDTO)
                .toList();
    }

    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toMovieDTO)
                .orElse(null);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}

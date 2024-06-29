package com.etraveli.movierental.mapper;

import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieCode;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public MovieDTO toMovieDTO(Movie movie) {
        if (movie == null) {
            return null;
        }

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setMovieId(movie.getMovieId());
        movieDTO.setCode(movie.getMovieCode().getCodeValue());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setReleaseYear(movie.getReleaseYear());

        return movieDTO;
    }

    public Movie toMovie(MovieDTO movieDTO) {
        if (movieDTO == null) {
            return null;
        }

        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setMovieId(movieDTO.getMovieId());
        movie.setMovieCode(MovieCode.fromString(movieDTO.getCode()));
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseYear(movieDTO.getReleaseYear());

        return movie;
    }
}

package com.etraveli.movierental.controller;

import com.etraveli.movierental.dto.MovieDTO;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieCode;
import com.etraveli.movierental.repository.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Movie movie;
    private MovieDTO movieDTO;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
        movie = new Movie();
        movie.setMovieId("F001");
        movie.setTitle("Integration Test Movie");
        movie.setMovieCode(MovieCode.fromString("regular"));
        movie.setGenre("Action");
        movie.setReleaseYear(2021);
        movie = movieRepository.save(movie);

        movieDTO = new MovieDTO();
        movieDTO.setMovieId("F001");
        movieDTO.setTitle("Integration Test Movie");
        movieDTO.setCode("regular");
        movieDTO.setGenre("Action");
        movieDTO.setReleaseYear(2021);
    }

    @Test
    void testGetAllMovies() throws Exception {
        mockMvc.perform(get("/api/v1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.movies.[0].title").value("Integration Test Movie"));
    }

    @Test
    void testGetMovieById() throws Exception {
        mockMvc.perform(get("/api/v1/movies/" + movie.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Integration Test Movie"));
    }

    @Test
    void testCreateMovie() throws Exception {
        MovieDTO newMovieDTO = new MovieDTO();
        newMovieDTO.setMovieId("F002");
        newMovieDTO.setCode("regular");
        newMovieDTO.setTitle("New Movie");
        newMovieDTO.setGenre("Comedy");
        newMovieDTO.setReleaseYear(2022);

        mockMvc.perform(post("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMovieDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Movie"));
    }

    @Test
    void testUpdateMovie() throws Exception {
        movieDTO.setTitle("Updated Title");

        mockMvc.perform(put("/api/v1/movies/" + movie.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    void testDeleteMovie() throws Exception {
        mockMvc.perform(delete("/api/v1/movies/" + movie.getId()))
                .andExpect(status().isNoContent());
    }
}

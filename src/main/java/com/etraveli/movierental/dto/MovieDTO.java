package com.etraveli.movierental.dto;

import com.etraveli.movierental.model.Movie;

import java.util.Objects;
import java.util.StringJoiner;

public class MovieDTO {

    private Long id;
    private String movieId;
    private String code;
    private String title;
    private String genre;
    private int releaseYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("movieId=" + movieId)
                .add("code='" + code + "'")
                .add("title='" + title + "'")
                .add("genre='" + genre + "'")
                .add("releaseYear" + releaseYear + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDTO movieDTO)) return false;
        return Objects.equals(id, movieDTO.id) &&
                Objects.equals(movieId, movieDTO.movieId) &&
                Objects.equals(code, movieDTO.code) &&
                Objects.equals(title, movieDTO.title) &&
                Objects.equals(genre, movieDTO.genre) &&
                Objects.equals(releaseYear, movieDTO.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, code, title, genre, releaseYear);
    }
}

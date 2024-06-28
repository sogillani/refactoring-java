package com.etraveli.movierental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieId;

    @Enumerated(EnumType.STRING)
    private MovieCode movieCode;

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

    public MovieCode getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(MovieCode movieCode) {
        this.movieCode = movieCode;
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
                .add("code='" + movieCode + "'")
                .add("title='" + title + "'")
                .add("genre='" + genre + "'")
                .add("releaseYear" + releaseYear + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(id, movie.id) &&
                Objects.equals(movieId, movie.movieId) &&
                Objects.equals(movieCode, movie.movieCode) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(releaseYear, movie.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, movieCode, title, genre, releaseYear);
    }
}

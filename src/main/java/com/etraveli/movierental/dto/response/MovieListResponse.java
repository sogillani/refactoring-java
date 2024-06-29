package com.etraveli.movierental.dto.response;

import com.etraveli.movierental.dto.MovieDTO;

import java.util.List;

public record MovieListResponse(List<MovieDTO> movies) {
}

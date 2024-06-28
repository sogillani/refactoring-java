package com.etraveli.movierental.service;

import com.etraveli.movierental.dto.CustomerDTO;
import com.etraveli.movierental.dto.InformationSlipRequest;
import com.etraveli.movierental.dto.MovieRentalInfo;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.model.MovieCode;
import com.etraveli.movierental.strategy.RentCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private MovieService movieService;

    @Spy
    private RentCalculator rentCalculator;

    @InjectMocks
    private RentalService rentalService;

    private Movie movieF001;
    private Movie movieF002;

    @BeforeEach
    void setUp() {
        movieF001 = new Movie();
        movieF001.setId(1L);
        movieF001.setMovieId("F001");
        movieF001.setTitle("You've Got Mail");
        movieF001.setMovieCode(MovieCode.REGULAR);
        movieF001.setGenre("Drama");
        movieF001.setReleaseYear(2020);

        movieF002 = new Movie();
        movieF002.setId(2L);
        movieF002.setTitle("Matrix");
        movieF002.setMovieId("F002");
        movieF002.setMovieCode(MovieCode.REGULAR);
        movieF002.setGenre("Crime");
        movieF002.setReleaseYear(1999);
    }

    @Test
    void testCreateInformationSlip() {
        when(movieService.findMovieByMovieId("F001")).thenReturn(movieF001);
        when(movieService.findMovieByMovieId("F002")).thenReturn(movieF002);

        CustomerDTO customerDTO = new CustomerDTO("C. U. Stomer");
        List<MovieRentalInfo> movieRentalInfos = List.of(new MovieRentalInfo("F001", 3),
                new MovieRentalInfo("F002", 1));
        InformationSlipRequest informationSlipRequest = new InformationSlipRequest(customerDTO, movieRentalInfos);

        String statement = rentalService.createInformationSlip(informationSlipRequest);
        assertEquals("Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n",
                statement);

    }
}

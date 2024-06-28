package com.etraveli.movierental.service;

import com.etraveli.movierental.dto.InformationSlipRequest;
import com.etraveli.movierental.model.Movie;
import com.etraveli.movierental.strategy.RentCalculator;
import com.etraveli.movierental.strategy.RentalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private static final Logger LOG = LoggerFactory.getLogger(RentalService.class);

    private final MovieService movieService;

    RentalService(MovieService movieService) {
        this.movieService = movieService;
    }

    public String createInformationSlip(InformationSlipRequest informationSlipRequest) {
        List<RentalResult> rentalResults = createRentalResult(informationSlipRequest);
        LOG.debug("Rental Results List: {}", rentalResults);

        double totalRent = rentalResults.stream()
                .mapToDouble(RentalResult::rent)
                .sum();

        int bonusPoints = rentalResults.stream()
                .mapToInt(RentalResult::point)
                .sum();

        int frequentEnterPoints = rentalResults.size();

        String joinedRentStatement = rentalResults.stream()
                .map(RentalResult::rentInfo)
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format(Locale.US, "Rental Record for %s%n%s%nAmount owed is %.1f%nYou earned %d frequent points%n",
                informationSlipRequest.customer().name(),
                joinedRentStatement,
                totalRent,
                bonusPoints + frequentEnterPoints);
    }

    public List<RentalResult> createRentalResult(InformationSlipRequest informationSlipRequest) {
        return informationSlipRequest.movieRentals().stream()
                .map(movieRentalInfo -> {
                    Movie movie = movieService.findMovieByMovieId(movieRentalInfo.movieId());

                    double rent = RentCalculator.calculateRent(movie.getMovieCode(), movieRentalInfo.days());

                    int bonusPoints = RentCalculator.calculateBonus(movie.getMovieCode(), movieRentalInfo.days());

                    String rentInfo = String.format(Locale.US, "\t%s\t%.1f", movie.getTitle(), rent);

                    return new RentalResult(rent, bonusPoints, rentInfo);
                })
                .toList();
    }
}

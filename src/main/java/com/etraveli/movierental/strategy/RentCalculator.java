package com.etraveli.movierental.strategy;

import com.etraveli.movierental.exception.ErrorCode;
import com.etraveli.movierental.exception.MovieCodeNotFoundException;
import com.etraveli.movierental.model.MovieCode;
import com.etraveli.movierental.strategy.function.BonusPointsStrategy;
import com.etraveli.movierental.strategy.function.RentalStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class RentCalculator {
    private final Map<MovieCode, RentalStrategy> rentalStrategies = new EnumMap<>(MovieCode.class);
    private final Map<MovieCode, BonusPointsStrategy> bonusStrategies = new EnumMap<>(MovieCode.class);

    public RentCalculator() {
        rentalStrategies.put(MovieCode.REGULAR, this::regularMovieRentCalculator);
        rentalStrategies.put(MovieCode.NEW, this::newMovieRentCalculator);
        rentalStrategies.put(MovieCode.CHILDREN, this::childrenMovieRentCalculator);

        bonusStrategies.put(MovieCode.NEW, this::newMovieBonusPointsCalculator);
    }

    /**
     * Calculate the rental fee for a movie based on its code and rental duration
     * @param movieCode defines the movie type
     * @param dayRented The number of days movie was rented
     * @return the calculated rental free for the movie
     * @throws MovieCodeNotFoundException if an unsupported movie code is encountered
     */
    public double calculateRent(MovieCode movieCode, int dayRented) {
        RentalStrategy strategy = rentalStrategies.get(movieCode);
        if (strategy == null) {
            throw new MovieCodeNotFoundException("Unknown movie code: " + movieCode, ErrorCode.MOVIE_CODE_NOT_FOUND);
        }
        return strategy.calculateRent(dayRented);
    }

    /**
     * Calculates the bonus points awarded for renting a movie based on its code and rental duration
     * @param movieCode defines the movie type
     * @param dayRented The number of days movie was rented
     * @return The calculated bonus points awarded for renting the movie.
     */
    public int calculateBonus(MovieCode movieCode, int dayRented) {
        BonusPointsStrategy bonusPointsStrategy = bonusStrategies.get(movieCode);
        if (bonusPointsStrategy == null) {
            return 0;
        }
        return bonusPointsStrategy.bonusPoints(dayRented);
    }

    private double regularMovieRentCalculator(int daysRented) {
        double rent = 2;
        if (daysRented > 2) {
            rent += (daysRented - 2) * 1.5;
        }
        return rent;
    }

    private double newMovieRentCalculator(int daysRented) {
        return daysRented * 3.0;
    }

    private double childrenMovieRentCalculator(int daysRented) {
        double rent = 1.5;
        if (daysRented > 3) {
            rent += (daysRented - 3.0) * 1.5;
        }
        return rent;
    }

    private int newMovieBonusPointsCalculator(int dayRented) {
        if (dayRented > 2) {
            return 1;
        }
        return 0;
    }
}

package com.etraveli.movierental.strategy;

import com.etraveli.movierental.exception.MovieCodeNotFoundException;
import com.etraveli.movierental.model.MovieCode;

import java.util.EnumMap;
import java.util.Map;

public class RentCalculator {
    private static final Map<MovieCode, RentalStrategy> rentalStrategies = new EnumMap<>(MovieCode.class);
    private static final Map<MovieCode, BonusPointsStrategy> bonusStrategies = new EnumMap<>(MovieCode.class);

    private RentCalculator() {}

    static {
        rentalStrategies.put(MovieCode.REGULAR, RentCalculator::regularMovieRentCalculator);
        rentalStrategies.put(MovieCode.NEW, RentCalculator::newMovieRentCalculator);
        rentalStrategies.put(MovieCode.CHILDREN, RentCalculator::childrenMovieRentCalculator);

        bonusStrategies.put(MovieCode.NEW, RentCalculator::newMovieBonusPointsCalculator);
    }

    private static double regularMovieRentCalculator(int daysRented) {
        double rent = 2;
        if (daysRented > 2) {
            rent += (daysRented - 2) * 1.5;
        }
        return rent;
    }

    private static double newMovieRentCalculator(int daysRented) {
        return daysRented * 3.0;
    }

    private static double childrenMovieRentCalculator(int daysRented) {
        double rent = 1.5;
        if (daysRented > 3) {
            rent += (daysRented - 3.0) * 1.5;
        }
        return rent;
    }

    private static int newMovieBonusPointsCalculator(int dayRented) {
        if (dayRented > 2) {
            return 1;
        }
        return 0;
    }

    public static double calculateRent(MovieCode movieCode, int dayRented) {
        RentalStrategy strategy = rentalStrategies.get(movieCode);
        if (strategy == null) {
            throw new MovieCodeNotFoundException("Unknown movie code: " + movieCode);
        }
        return strategy.calculateRent(dayRented);
    }

    public static int calculateBonus(MovieCode movieCode, int dayRented) {
        BonusPointsStrategy bonusPointsStrategy = bonusStrategies.get(movieCode);
        if (bonusPointsStrategy == null) {
            return 0;
        }
        return bonusPointsStrategy.bonusPoints(dayRented);
    }
}

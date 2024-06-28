package com.etraveli.movierental.strategy;

import com.etraveli.movierental.exception.MovieCodeNotFoundException;
import com.etraveli.movierental.model.MovieCode;
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

    public double calculateRent(MovieCode movieCode, int dayRented) {
        RentalStrategy strategy = rentalStrategies.get(movieCode);
        if (strategy == null) {
            throw new MovieCodeNotFoundException("Unknown movie code: " + movieCode);
        }
        return strategy.calculateRent(dayRented);
    }

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

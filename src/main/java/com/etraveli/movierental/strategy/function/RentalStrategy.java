package com.etraveli.movierental.strategy.function;

@FunctionalInterface
public interface RentalStrategy {
    double calculateRent(int daysRented);
}

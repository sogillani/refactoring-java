package com.etraveli.movierental.strategy;

@FunctionalInterface
public interface RentalStrategy {
    double calculateRent(int daysRented);
}

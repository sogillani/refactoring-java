package com.etraveli.movierental.strategy;

@FunctionalInterface
public interface BonusPointsStrategy {
    int bonusPoints(int daysRented);
}

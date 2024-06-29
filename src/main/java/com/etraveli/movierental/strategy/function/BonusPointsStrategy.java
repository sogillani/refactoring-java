package com.etraveli.movierental.strategy.function;

@FunctionalInterface
public interface BonusPointsStrategy {
    int bonusPoints(int daysRented);
}

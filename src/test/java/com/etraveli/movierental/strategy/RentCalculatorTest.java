package com.etraveli.movierental.strategy;

import com.etraveli.movierental.model.MovieCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class RentCalculatorTest {

    @InjectMocks
    private RentCalculator rentCalculator;

    @Test
    void testCalculateRentForRegularMovies() {
        assertEquals(2.0, rentCalculator.calculateRent(MovieCode.REGULAR, 2));
        assertEquals(3.5, rentCalculator.calculateRent(MovieCode.REGULAR, 3));
    }

    @Test
    void testCalculateRentForNewMovies() {
        assertEquals(9.0, rentCalculator.calculateRent(MovieCode.NEW, 3));
    }

    @Test
    void testCalculateRentForChildrenMovies() {
        assertEquals(1.5, rentCalculator.calculateRent(MovieCode.CHILDREN, 3));
        assertEquals(4.5, rentCalculator.calculateRent(MovieCode.CHILDREN, 5));
    }

    @Test
    void testCalculateBonus() {
        assertEquals(1, rentCalculator.calculateBonus(MovieCode.NEW, 3));
        assertEquals(0, rentCalculator.calculateBonus(MovieCode.NEW, 2));
    }

}

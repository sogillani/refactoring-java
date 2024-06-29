package com.etraveli.movierental.dto;

import java.util.List;

public record InformationSlipRequest(CustomerDTO customer, List<MovieRentalInfo> movieRentals) {
}

package com.etraveli.movierental.controller;

import com.etraveli.movierental.dto.InformationSlipRequest;
import com.etraveli.movierental.dto.response.RentalStatementResponse;
import com.etraveli.movierental.service.RentalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller handles rental information slip for rented movies
 */
@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {
    private static final Logger LOG = LoggerFactory.getLogger(RentalController.class);

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * Generate the rental information slip for rented movies
     * @param informationSlipRequest object with rented movies and customer name
     * @return A {@link RentalStatementResponse} object with rented movies information which contains total rent and earned points
     */
    @PostMapping("/statement")
    public ResponseEntity<RentalStatementResponse> generateStatement(@RequestBody InformationSlipRequest informationSlipRequest) {
        LOG.debug("Information Slip Request: {}", informationSlipRequest);
        return ResponseEntity.ok(new RentalStatementResponse(rentalService.createInformationSlip(informationSlipRequest)));
    }
}

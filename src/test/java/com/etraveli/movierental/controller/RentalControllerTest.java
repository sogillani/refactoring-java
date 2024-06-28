package com.etraveli.movierental.controller;

import com.etraveli.movierental.dto.CustomerDTO;
import com.etraveli.movierental.dto.InformationSlipRequest;
import com.etraveli.movierental.dto.MovieRentalInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGenerateStatement() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO("C. U. Stomer");
        List<MovieRentalInfo> movieRentalInfos = List.of(new MovieRentalInfo("F001", 3),
                new MovieRentalInfo("F002", 1));
        InformationSlipRequest informationSlipRequest = new InformationSlipRequest(customerDTO, movieRentalInfos);

        mockMvc.perform(post("/api/v1/rentals/statement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(informationSlipRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statement").value("Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n"));

    }
}

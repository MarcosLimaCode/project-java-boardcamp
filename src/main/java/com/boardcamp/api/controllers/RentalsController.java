package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.services.RentalsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

  final RentalsService rentalsService;

  RentalsController(RentalsService rentalsService) {
    this.rentalsService = rentalsService;
  }

  @GetMapping()
  public ResponseEntity<Object> getRentals() {
    return ResponseEntity.status(HttpStatus.OK).body(rentalsService.getRentals());
  }

  @PostMapping()
  public RentalsModel createRentals(RentalsDTO body) {
    RentalsModel rental = new RentalsModel(body);
    return rental;
  }
}

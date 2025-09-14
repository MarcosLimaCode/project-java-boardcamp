package com.boardcamp.api.controllers;

import com.boardcamp.api.services.RentalsServiceTemp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalsControllerTemp {

  final RentalsServiceTemp rentalsService;

  RentalsControllerTemp(RentalsServiceTemp rentalsService) {
    this.rentalsService = rentalsService;
  }
}

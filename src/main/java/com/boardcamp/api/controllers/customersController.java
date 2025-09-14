package com.boardcamp.api.controllers;

import com.boardcamp.api.services.CustomersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

  final CustomersService customersService;

  CustomersController(CustomersService customersService) {
    this.customersService = customersService;
  }
  ;
}

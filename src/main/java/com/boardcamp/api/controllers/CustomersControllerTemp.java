package com.boardcamp.api.controllers;

import com.boardcamp.api.services.CustomersServiceTemp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersControllerTemp {

  final CustomersServiceTemp customersService;

  CustomersControllerTemp(CustomersServiceTemp customersService) {
    this.customersService = customersService;
  }
  ;
}

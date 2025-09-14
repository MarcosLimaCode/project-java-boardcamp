package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.services.CustomersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

  final CustomersService customersService;

  CustomersController(CustomersService customersService) {
    this.customersService = customersService;
  }

  @GetMapping()
  public ResponseEntity<Object> getCustomers() {
    return ResponseEntity.status(HttpStatus.OK).body(customersService.getCustomers());
  }

  @PostMapping()
  public ResponseEntity<Object> createCustomers(@RequestBody @Valid CustomerDTO body) {
    return ResponseEntity.status(HttpStatus.OK).body(customersService.createCustomers(body));
  }
}

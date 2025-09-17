package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.services.CustomersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/{id}")
  public ResponseEntity<Object> getCustomersById(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customersService.getCustomersById(id));
  }

  @PostMapping()
  public ResponseEntity<Object> createCustomers(@RequestBody @Valid CustomersDTO body) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customersService.createCustomers(body));
  }
}

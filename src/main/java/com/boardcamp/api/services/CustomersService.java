package com.boardcamp.api.services;

import com.boardcamp.api.repositories.CustomersRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
  final CustomersRepository customersRepository;

  CustomersService(CustomersRepository customersRepository) {
    this.customersRepository = customersRepository;
  }
}

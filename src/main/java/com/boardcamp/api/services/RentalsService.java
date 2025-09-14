package com.boardcamp.api.services;

import com.boardcamp.api.repositories.RentalsRepository;
import org.springframework.stereotype.Service;

@Service
public class RentalsService {
  final RentalsRepository rentalsRepository;

  RentalsService(RentalsRepository rentalsRepository) {
    this.rentalsRepository = rentalsRepository;
  }
}

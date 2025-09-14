package com.boardcamp.api.services;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.RentalsRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RentalsService {
  final RentalsRepository rentalsRepository;

  RentalsService(RentalsRepository rentalsRepository) {
    this.rentalsRepository = rentalsRepository;
  }

  public List<RentalsModel> getRentals() {
    return rentalsRepository.findAll();
  }

  public RentalsModel createRentals(RentalsDTO body) {
    RentalsModel rental = new RentalsModel(body);
    return rental;
  }
}

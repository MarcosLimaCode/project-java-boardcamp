package com.boardcamp.api.services;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class RentalsService {
  final RentalsRepository rentalsRepository;
  final CustomersRepository customersRepository;
  final GamesRepository gamesRepository;

  RentalsService(
      RentalsRepository rentalsRepository,
      CustomersRepository customersRepository,
      GamesRepository gamesRepository) {
    this.rentalsRepository = rentalsRepository;
    this.customersRepository = customersRepository;
    this.gamesRepository = gamesRepository;
  }

  public List<RentalsModel> getRentals() {
    return rentalsRepository.findAll();
  }

  public RentalsModel createRentals(RentalsDTO body) {
    Optional<CustomersModel> customer = customersRepository.findById(body.getCustomerId());
    Optional<GamesModel> game = gamesRepository.findById(body.getGameId());

    RentalsModel rental = new RentalsModel(body, customer.get(), game.get());
    rentalsRepository.save(rental);
    return rental;
  }
}

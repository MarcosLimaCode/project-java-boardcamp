package com.boardcamp.api.services;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.errors.NotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import java.util.List;
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
    CustomersModel customer =
        customersRepository
            .findById(body.getCustomerId())
            .orElseThrow(() -> new NotFoundException("Customer not found."));
    GamesModel game =
        gamesRepository
            .findById(body.getGameId())
            .orElseThrow(() -> new ConflictException("Game not found"));

    Integer originalPrice = body.getDaysRented() * game.getPricePerDay();

    RentalsModel rental = new RentalsModel(body, customer, game, originalPrice);
    rentalsRepository.save(rental);
    return rental;
  }
}

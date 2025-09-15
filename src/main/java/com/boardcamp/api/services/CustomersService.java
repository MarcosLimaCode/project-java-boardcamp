package com.boardcamp.api.services;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
  final CustomersRepository customersRepository;

  CustomersService(CustomersRepository customersRepository) {
    this.customersRepository = customersRepository;
  }

  public List<CustomersModel> getCustomers() {
    return customersRepository.findAll();
  }

  public Optional<CustomersModel> getCustomersById(Long id) {
    Optional<CustomersModel> customer = customersRepository.findById(id);

    if (!customer.isPresent()) {
      return Optional.empty();
    }

    return customer;
  }

  public CustomersModel createCustomers(CustomersDTO body) {
    CustomersModel customer = new CustomersModel(body);

    if(customersRepository.existsByCpf(body.getCpf())) {
      throw new ConflictException("CPF is already in use.");
    }
    customersRepository.save(customer);
    return customer;
  }
}

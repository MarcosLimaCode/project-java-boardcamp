package com.boardcamp.api.services;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.errors.NotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import java.util.List;
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

  public CustomersModel getCustomersById(Long id) {
    CustomersModel customer =
        customersRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Customer not found."));

    return customer;
  }

  public CustomersModel createCustomers(CustomersDTO body) {
    CustomersModel customer = new CustomersModel(body);

    if (customersRepository.existsByCpf(body.getCpf())) {
      throw new ConflictException("CPF is already in use.");
    }
    customersRepository.save(customer);
    return customer;
  }
}

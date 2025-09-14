package com.boardcamp.api.services;

import com.boardcamp.api.dtos.CustomerDTO;
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

  public CustomersModel createCustomers(CustomerDTO body) {

    CustomersModel customer = new CustomersModel(body);
    customersRepository.save(customer);
    return customer;
  }
}

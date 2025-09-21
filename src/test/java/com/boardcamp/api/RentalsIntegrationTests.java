package com.boardcamp.api;

import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RentalsIntegrationTests {

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private CustomersRepository customersRepository;
  @Autowired private RentalsRepository rentalsRepository;
  @Autowired private GamesRepository gamesRepository;

  @BeforeEach
  @AfterEach
  void cleanUpDatabase() {
    customersRepository.deleteAll();
    rentalsRepository.deleteAll();
    gamesRepository.deleteAll();
  }

  @Test
  void givenRepeatedCpf_whenCreatingCustomers_thenThrowsErrors() {
    // given

    // when

    // then
  }

  @Test
  void givenValidCustomers_whenCreatingCustomers_thenCreatesCustomer() {
    // given

    // when

    // then
  }

  @Test
  void givenGetCommand_whenGetCustomers_thenReturnAllCustomer() {
    // given

    // when

    // then
  }
}

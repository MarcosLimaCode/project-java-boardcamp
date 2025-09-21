package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CustomersIntegrationTests {

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private CustomersRepository customersRepository;

  @BeforeEach
  @AfterEach
  void cleanUpDatabase() {
    customersRepository.deleteAll();
  }

  @Test
  void givenRepeatedCpf_whenCreatingCustomers_thenThrowsErrors() {
    // given
    CustomersModel customer = new CustomersModel(null, "teste", "1111111111", "11111111111");
    customersRepository.save(customer);

    CustomersDTO newCustomer = new CustomersDTO("novo", "1111111112", "11111111111");

    HttpEntity<CustomersDTO> body = new HttpEntity<>(newCustomer);

    // when

    ResponseEntity<String> response =
        restTemplate.exchange("/customers", HttpMethod.POST, body, String.class);

    // then
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertEquals(1, customersRepository.count());
    assertEquals("CPF is already in use.", response.getBody());
  }

  @Test
  void givenValidCustomers_whenCreatingCustomers_thenCreatesCustomer() {
    // given
    CustomersModel customer = new CustomersModel(null, "teste", "1111111111", "11111111111");

    HttpEntity<CustomersModel> body = new HttpEntity<>(customer);

    // when

    ResponseEntity<CustomersModel> response =
        restTemplate.exchange("/customers", HttpMethod.POST, body, CustomersModel.class);

    // then

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, customersRepository.count());
  }

  @Test
  void givenGetCommand_whenGetCustomers_thenReturnAllCustomer() {
    // given
    CustomersModel customer = new CustomersModel(null, "teste", "1111111111", "11111111111");
    customersRepository.save(customer);

    // when

    ResponseEntity<CustomersModel[]> response =
        restTemplate.exchange("/customers", HttpMethod.GET, null, CustomersModel[].class);

    // then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, customersRepository.count());
  }
}

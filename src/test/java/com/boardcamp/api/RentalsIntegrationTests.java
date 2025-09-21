package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import java.util.List;
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
  void givenGetCommand_whenGetRentals_thenReturnAllRentals() {
    // given
    CustomersDTO customer = new CustomersDTO("Test", "Test", "Test");
    CustomersModel createCustomer = new CustomersModel(customer);

    GamesDTO game = new GamesDTO("Test", "Test", 10, 10);
    GamesModel createGame = new GamesModel(game);

    RentalsDTO rental = new RentalsDTO(createCustomer.getId(), createGame.getId(), 10);

    List<RentalsModel> allRentals =
        List.of(new RentalsModel(rental, createCustomer, createGame, 10));

    HttpEntity<List<RentalsModel>> body = new HttpEntity<>(allRentals);

    // when

    ResponseEntity<RentalsModel[]> response =
        restTemplate.exchange("/rentals", HttpMethod.GET, body, RentalsModel[].class);

    // then

    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void givenInvalidRental_whenDeleteRental_thenRemoveRental() {
    // given
    Long id = 1L;

    // when

    ResponseEntity<String> response =
        restTemplate.exchange("/rentals" + id, HttpMethod.DELETE, null, String.class);

    // then

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void givenInvalidRental_whenGettingRental_thenGetRental() {
    // given
    Long id = 1L;

    // when

    ResponseEntity<String> response =
        restTemplate.exchange("/rentals" + id, HttpMethod.GET, null, String.class);

    // then

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}

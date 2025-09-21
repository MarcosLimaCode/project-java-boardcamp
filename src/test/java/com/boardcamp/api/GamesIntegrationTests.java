package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;
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
public class GamesIntegrationTests {

  @Autowired private TestRestTemplate restTemplate;
  @Autowired private GamesRepository gamesRepository;

  @BeforeEach
  @AfterEach
  void cleanUpDatabase() {
    gamesRepository.deleteAll();
  }

  @Test
  void givenRepeatedName_whenCreatingGames_thenThrowsErrors() {
    // given
    GamesModel game = new GamesModel(null, "Teste", "Teste", 10, 10);
    gamesRepository.save(game);

    GamesDTO newGame = new GamesDTO("Teste", "Teste", 10, 10);

    HttpEntity<GamesDTO> body = new HttpEntity<>(newGame);

    // when

    ResponseEntity<String> response =
        restTemplate.exchange("/games", HttpMethod.POST, body, String.class);

    // then

    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    assertEquals(1, gamesRepository.count());
    assertEquals("Game is already registred.", response.getBody());
  }

  @Test
  void givenValidGame_whenCreatingGame_thenCreatesGame() {
    // given
    GamesModel game = new GamesModel(null, "teste", "teste", 10, 10);

    HttpEntity<GamesModel> body = new HttpEntity<>(game);

    // when

    ResponseEntity<GamesModel> response =
        restTemplate.exchange("/games", HttpMethod.POST, body, GamesModel.class);

    // then

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, gamesRepository.count());
  }

  @Test
  void givenGetCommand_whenGetGames_thenReturnAllGames() {
    // given
    GamesModel game = new GamesModel(null, "teste", "teste", 10, 10);
    gamesRepository.save(game);

    // when

    ResponseEntity<GamesModel[]> response =
        restTemplate.exchange("/games", HttpMethod.GET, null, GamesModel[].class);

    // then

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, gamesRepository.count());
  }
}

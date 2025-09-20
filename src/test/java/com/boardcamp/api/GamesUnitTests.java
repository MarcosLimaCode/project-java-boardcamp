package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.services.GamesService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GamesUnitTests {
  @InjectMocks private GamesService gamesService;
  @Mock private GamesRepository gamesRepository;

  @Test
  void givenRepeatedName_whenCreatingGames_thenThrowsErrors() {
    // given
    GamesDTO game = new GamesDTO("Teste", "Teste", 10, 10);

    doReturn(true).when(gamesRepository).existsByName(any());

    // when
    ConflictException exception =
        assertThrows(ConflictException.class, () -> gamesService.createGames(game));

    // then

    verify(gamesRepository, times(0)).save(any());
    assertNotNull(exception);
    assertEquals("Game is already registred.", exception.getMessage());
  }

  @Test
  void givenValidGame_whenCreatingGame_thenCreatesGame() {
    // given
    GamesDTO game = new GamesDTO("Test", "Test", 10, 10);
    GamesModel newGame = new GamesModel(game);

    doReturn(false).when(gamesRepository).existsByName(any());
    doReturn(newGame).when(gamesRepository).save(any());

    // when
    GamesModel results = gamesRepository.save(newGame);

    // then
    verify(gamesRepository, times(1)).save(any());
    assertEquals(newGame, results);
  }

  @Test
  void givenGetCommand_whenGetGames_thenReturnAllGames() {
    // given
    List<GamesModel> games = List.of(new GamesModel(1L, "test", "test", 10, 10));

    doReturn(games).when(gamesRepository).findAll();

    // when
    List<GamesModel> getAllGames = gamesRepository.findAll();

    // then
    verify(gamesRepository, times(1)).findAll();
    assertEquals(games, getAllGames);
  }
}

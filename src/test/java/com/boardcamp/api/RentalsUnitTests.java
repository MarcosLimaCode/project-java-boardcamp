package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.errors.NotFoundException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import com.boardcamp.api.services.RentalsService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentalsUnitTests {
  @InjectMocks private RentalsService rentalsService;
  @Mock private RentalsRepository rentalsRepository;
  @Mock private CustomersRepository customersRepository;
  @Mock private GamesRepository gamesRepository;

  @Test
  void givenGetCommand_whenGetRentals_thenReturnAllRentals() {
    // given
    CustomersDTO customer = new CustomersDTO("Test", "Test", "Test");
    CustomersModel createCustomer = new CustomersModel(customer);

    GamesDTO game = new GamesDTO("Test", "Test", 10, 10);
    GamesModel createGame = new GamesModel(game);

    RentalsDTO rental = new RentalsDTO(1L, 2L, 10);

    List<RentalsModel> allRentals =
        List.of(new RentalsModel(rental, createCustomer, createGame, 10));

    doReturn(allRentals).when(rentalsRepository).findAll();

    // when
    List<RentalsModel> getAllRentals = rentalsRepository.findAll();

    // then
    verify(rentalsRepository, times(1)).findAll();
    assertEquals(allRentals, getAllRentals);
  }

  @Test
  void givenExistingRental_whenDeletingRentals_thenRemoveRental() {
    // given
    CustomersDTO customer = new CustomersDTO("Test", "Test", "Test");
    CustomersModel createCustomer = new CustomersModel(customer);

    GamesDTO game = new GamesDTO("Test", "Test", 10, 10);
    GamesModel createGame = new GamesModel(game);

    RentalsDTO rental = new RentalsDTO(1L, 2L, 10);

    RentalsModel createRenal = new RentalsModel(rental, createCustomer, createGame, 10);

    doReturn(Optional.of(createRenal)).when(rentalsRepository).findById(any());

    // when
    rentalsRepository.deleteById(any());

    // then
    verify(rentalsRepository, times(1)).deleteById(null);
  }

  @Test
  void givenNonExistingRental_whenDeletingRentals_thenThrowErrors() {
    // given

    doReturn(Optional.empty()).when(rentalsRepository).findById(any());

    // when
    NotFoundException exception =
        assertThrows(NotFoundException.class, () -> rentalsService.deleteRental(any()));

    // then
    verify(rentalsRepository, times(0)).deleteById(any());
    assertNotNull(exception);
    assertEquals("Rental not found.", exception.getMessage());
  }
}

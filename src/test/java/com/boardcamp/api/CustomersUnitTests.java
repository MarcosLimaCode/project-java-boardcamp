package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.services.CustomersService;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomersUnitTests {
  @InjectMocks private CustomersService customersService;
  @Mock private CustomersRepository customersRepository;

  @Test
  void givenRepeatedCpf_whenCreatingCustomers_thenThrowsErrors() {
    // given
    CustomersDTO customer = new CustomersDTO("Test", "Test", "Test");

    doReturn(true).when(customersRepository).existsByCpf(any());
    // when
    ConflictException exception =
        assertThrows(ConflictException.class, () -> customersService.createCustomers(customer));

    // then

    verify(customersRepository, times(0)).save(any());
    assertNotNull(exception);
    assertEquals("CPF is already in use.", exception.getMessage());
  }

  @Test
  void givenValidCustomers_whenCreatingCustomers_thenCreatesCustomer() {
    // given
    CustomersDTO customer = new CustomersDTO("Test", "Test", "Test");
    CustomersModel newCustomer = new CustomersModel(customer);

    doReturn(false).when(customersRepository).existsByCpf(any());
    doReturn(newCustomer).when(customersRepository).save(any());

    // when
    CustomersModel results = customersService.createCustomers(customer);

    // then
    verify(customersRepository, times(1)).save(any());
    assertEquals(newCustomer, results);
  }

  @Test
  void givenGetCommand_whenGetCustomers_thenReturnAllCustomer() {
    // given
    List<CustomersModel> customers = List.of(new CustomersModel(1L, "test", "test", "test"));

    doReturn(customers).when(customersRepository).findAll();

    // when
    List<CustomersModel> getAllCustomers = customersRepository.findAll();

    // then
    verify(customersRepository, times(1)).findAll();
    assertEquals(customers, getAllCustomers);
  }

  @Test
  void givenValidId_whenGetCustomersById_thenReturnCustomer() {
    // given
    CustomersModel customer = new CustomersModel(1L, "test", "test", "test");

    doReturn(Optional.of(customer)).when(customersRepository).findById(1L);

    // when
    Optional<CustomersModel> findCustomer = customersRepository.findById(customer.getId());

    // then
    verify(customersRepository, times(1)).findById(any());
    assertEquals(Optional.of(customer), findCustomer);
  }
}

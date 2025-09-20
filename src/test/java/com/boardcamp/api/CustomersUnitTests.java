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
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.services.CustomersService;
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
}

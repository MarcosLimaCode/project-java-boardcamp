package com.boardcamp.api.models;

import java.time.LocalDate;

public class rentalsModel {
  private Long id;
  private Long customerId;
  private Long gameId;
  private LocalDate rentDate;
  private Integer daysRented;
  private LocalDate returnDate;
  private Integer originalPrice;
  private Integer delayFee;
}

// id, customerId, gameId, rentDate, daysRented, returnDate, originalPrice, delayFee

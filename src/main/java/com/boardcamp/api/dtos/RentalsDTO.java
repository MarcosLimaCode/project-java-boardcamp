package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalsDTO {
  @NotBlank @NotEmpty @NotNull private Integer customerId;
  @NotBlank @NotEmpty @NotNull private Integer gameId;
  @NotBlank @NotEmpty @NotNull private Integer daysRented;
}

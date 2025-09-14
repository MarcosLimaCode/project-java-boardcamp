package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomersDTO {
  @NotBlank @NotEmpty @NotNull private String name;
  @NotBlank @NotEmpty @NotNull private String phone;
  @NotBlank @NotEmpty @NotNull private String cpf;
}

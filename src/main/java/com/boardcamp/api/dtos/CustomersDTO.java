package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomersDTO {
  @NotBlank @NotEmpty @NotNull private String name;

  @NotBlank
  @NotEmpty
  @NotNull
  @Length(min = 10, max = 11)
  private String phone;

  @NotBlank
  @NotEmpty
  @NotNull
  @Length(min = 11, max = 11)
  private String cpf;
}

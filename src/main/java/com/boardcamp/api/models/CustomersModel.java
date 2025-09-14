package com.boardcamp.api.models;

import com.boardcamp.api.dtos.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class CustomersModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Size(min = 10, max = 11)
  private String phone;

  @Column(nullable = false, length = 11)
  private String cpf;

  public CustomersModel(CustomerDTO dto) {
    this.name = dto.getName();
    this.phone = dto.getPhone();
    this.cpf = dto.getCpf();
  }
}

package com.boardcamp.api.models;

import com.boardcamp.api.dtos.RentalsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rentals")
public class RentalsModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private Integer customerId;

  @Column(nullable = false)
  private Integer gameId;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column private Integer daysRented;

  @Column private LocalDate returnDate;

  @Column private Integer originalPrice;

  @Column private Integer delayFee;

  public RentalsModel(RentalsDTO dto) {
    this.customerId = dto.getCustomerId();
    this.gameId = dto.getGameId();
    this.daysRented = dto.getDaysRented();
  }
}

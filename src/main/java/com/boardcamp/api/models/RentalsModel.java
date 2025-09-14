package com.boardcamp.api.models;

import com.boardcamp.api.dtos.RentalsDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @JsonIgnore
  @Column(nullable = false)
  private Long customerId;

  @JsonIgnore
  @Column(nullable = false)
  private Long gameId;

  @Column(nullable = false)
  private LocalDate rentDate;

  @Column private Integer daysRented;

  @Column private LocalDate returnDate;

  @Column private Integer originalPrice;

  @Column private Integer delayFee;

  @ManyToOne
  @JoinColumn(name = "customer")
  private CustomersModel customer;

  @ManyToOne
  @JoinColumn(name = "game")
  private GamesModel game;

  public RentalsModel(
      RentalsDTO dto, CustomersModel customer, GamesModel game, Integer originalPrice) {
    this.customerId = dto.getCustomerId();
    this.gameId = dto.getGameId();
    this.rentDate = LocalDate.now();
    this.daysRented = dto.getDaysRented();
    this.originalPrice = originalPrice;
    this.delayFee = 0;
    this.customer = customer;
    this.game = game;
  }
}

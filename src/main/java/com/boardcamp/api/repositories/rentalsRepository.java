package com.boardcamp.api.repositories;

import com.boardcamp.api.models.RentalsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<RentalsModel, Long> {}

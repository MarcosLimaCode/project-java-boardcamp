package com.boardcamp.api.repositories;

import com.boardcamp.api.models.rentalsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rentalsRepository extends JpaRepository<rentalsModel, Long> {}

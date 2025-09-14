package com.boardcamp.api.repositories;

import com.boardcamp.api.models.RentalsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<RentalsModel, Long> {}

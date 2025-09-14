package com.boardcamp.api.repositories;

import com.boardcamp.api.models.CustomersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<CustomersModel, Long> {}

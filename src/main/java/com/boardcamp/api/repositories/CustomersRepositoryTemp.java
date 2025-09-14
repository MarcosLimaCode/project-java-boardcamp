package com.boardcamp.api.repositories;

import com.boardcamp.api.models.CustomersModelTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepositoryTemp extends JpaRepository<CustomersModelTemp, Long> {}

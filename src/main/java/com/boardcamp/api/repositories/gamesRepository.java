package com.boardcamp.api.repositories;

import com.boardcamp.api.models.gamesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface gamesRepository extends JpaRepository<gamesModel, Long> {}

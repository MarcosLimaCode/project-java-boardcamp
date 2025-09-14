package com.boardcamp.api.repositories;

import com.boardcamp.api.models.GamesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<GamesModel, Long> {}

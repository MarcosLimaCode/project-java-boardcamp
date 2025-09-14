package com.boardcamp.api.repositories;

import com.boardcamp.api.models.GamesModelTemp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepositoryTemp extends JpaRepository<GamesModelTemp, Long> {}

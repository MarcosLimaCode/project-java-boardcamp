package com.boardcamp.api.repositories;

import com.boardcamp.api.models.GamesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamesRepository extends JpaRepository<GamesModel, Long> {
  boolean existsByName(String name);
}

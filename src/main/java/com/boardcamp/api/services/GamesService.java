package com.boardcamp.api.services;

import com.boardcamp.api.repositories.GamesRepository;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
  final GamesRepository gamesRepository;

  GamesService(GamesRepository gamesRepository) {
    this.gamesRepository = gamesRepository;
  }
}

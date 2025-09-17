package com.boardcamp.api.services;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.errors.ConflictException;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GamesService {
  final GamesRepository gamesRepository;

  GamesService(GamesRepository gamesRepository) {
    this.gamesRepository = gamesRepository;
  }

  public List<GamesModel> getGames() {
    return gamesRepository.findAll();
  }

  public GamesModel createGames(GamesDTO body) {
    boolean findGame = gamesRepository.existsByName(body.getName());
    GamesModel game = new GamesModel(body);

    if (findGame) {
      throw new ConflictException("Game is already registred.");
    }

    gamesRepository.save(game);
    return game;
  }
}

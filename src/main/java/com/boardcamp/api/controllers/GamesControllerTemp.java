package com.boardcamp.api.controllers;

import com.boardcamp.api.services.GamesServiceTemp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GamesControllerTemp {

  final GamesServiceTemp gamesService;

  GamesControllerTemp(GamesServiceTemp gamesService) {
    this.gamesService = gamesService;
  }
}

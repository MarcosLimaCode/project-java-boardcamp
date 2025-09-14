package com.boardcamp.api.controllers;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.services.GamesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GamesController {

  final GamesService gamesService;

  GamesController(GamesService gamesService) {
    this.gamesService = gamesService;
  }

  @GetMapping()
  public ResponseEntity<Object> getCustomers() {
    return ResponseEntity.status(HttpStatus.OK).body(gamesService.getGames());
  }

  @PostMapping()
  public ResponseEntity<Object> createGames(@RequestBody @Valid GamesDTO body) {
    return ResponseEntity.status(HttpStatus.OK).body(gamesService.createGames(body));
  }
}

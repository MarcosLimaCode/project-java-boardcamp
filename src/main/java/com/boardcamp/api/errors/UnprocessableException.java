package com.boardcamp.api.errors;

public class UnprocessableException extends RuntimeException {
  public UnprocessableException(String message) {
    super(message);
  }
}

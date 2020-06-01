package ch.bfh.bti7081.s2020.orange.ui.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}

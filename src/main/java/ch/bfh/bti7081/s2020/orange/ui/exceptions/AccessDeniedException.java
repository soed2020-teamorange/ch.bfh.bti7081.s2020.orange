package ch.bfh.bti7081.s2020.orange.ui.exceptions;

public class AccessDeniedException extends RuntimeException {

  public AccessDeniedException() {
  }

  public AccessDeniedException(final String message) {
    super(message);
  }
}

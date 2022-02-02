package com.example.pokedemo.controller.exception;

public class ExternalServiceException extends RuntimeException {

  private final ExternalServiceError externalServiceError;

  public ExternalServiceException(final String message) {
    super(message);
    this.externalServiceError = null;
  }

  public ExternalServiceException(final String message, final ExternalServiceError externalServiceError) {
    super(message);
    this.externalServiceError = externalServiceError;
  }

  public ExternalServiceError getExternalServiceError() {
    return externalServiceError;
  }
}

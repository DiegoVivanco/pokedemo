package com.example.pokedemo.configuration;


import com.example.pokedemo.controller.exception.BadRequestException;
import com.example.pokedemo.controller.exception.ExternalServiceException;
import com.example.pokedemo.dto.MessageDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionConfig {

  @ExceptionHandler(ExternalServiceException.class)
  public ResponseEntity<MessageDto> notFoundException(Exception e) {
    return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({BadRequestException.class, DataIntegrityViolationException.class})
  public ResponseEntity<MessageDto> badRequestException(Exception e) {
    return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}

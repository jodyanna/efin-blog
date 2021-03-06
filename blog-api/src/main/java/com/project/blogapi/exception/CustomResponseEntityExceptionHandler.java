package com.project.blogapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception,
      HttpHeaders httpHeaders,
      HttpStatus httpStatus,
      WebRequest request
  ) {
    List<ObjectError> errors = exception.getBindingResult().getAllErrors();
    List<String> errorMessages = errors
        .stream()
        .map(ObjectError::getDefaultMessage)
        .collect(Collectors.toList());
    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Invalid payload", errorMessages);

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleResourceNotFoundException(
      ResourceNotFoundException exception, WebRequest request
  ) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), Collections.singletonList(request.getDescription(false)));

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}

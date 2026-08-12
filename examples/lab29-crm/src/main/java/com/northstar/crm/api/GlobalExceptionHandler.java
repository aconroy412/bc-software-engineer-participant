package com.northstar.crm.api;

import com.northstar.crm.dto.ErrorResponse;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {

    List<ErrorResponse.FieldViolation> violations = 
      ex.getBindingResult()
        .getFieldErrors()
        .stream()
          .map(error -> new ErrorResponse.FieldViolation(
            error.getField(),
            error.getDefaultMessage()
          ))
          .toList();

    ErrorResponse response = new ErrorResponse(); 
    response.setStatus(400); response.setError("Bad Request"); 
    response.setMessage(ex.getMessage()); 
    response.setCorrelationId(request.getHeader("X-Correlation-Id")); 
    response.setViolations(violations);

    return ResponseEntity.badRequest().body(response);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(IllegalArgumentException ex, WebRequest request) {
    // TODO: 404 envelope for "Customer not found" (CUS-9999 path)

    ErrorResponse error = new ErrorResponse();

    error.setStatus(404);
    error.setError("Not Found");
    error.setMessage(ex.getMessage());
    error.setCorrelationId(request.getHeader("X-Correlation-Id"));

    return ResponseEntity.status(404).body(error);
  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<ErrorResponse> handleConflict(IllegalStateException ex, WebRequest request) {
    
    ErrorResponse error = new ErrorResponse();

    error.setStatus(409);
    error.setError("Conflict");
    error.setMessage(ex.getMessage());
    error.setCorrelationId(request.getHeader("X-Correlation-Id"));

    return ResponseEntity.status(409).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleSafe500(Exception ex, WebRequest request) {
    // TODO: 500 without leaking stack traces / SQL
    ErrorResponse error = new ErrorResponse();

    error.setStatus(500);
    error.setError("Conflict");
    error.setMessage("An unexpected Error occurred");
    error.setCorrelationId("X-Correlation-Id");

    return ResponseEntity.status(500).body(error);
  }
}

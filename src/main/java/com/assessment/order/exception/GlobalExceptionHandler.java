package com.assessment.order.exception;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ApplicationErrorResponse> handleApplicationException(ApplicationException exception) {
    return ResponseEntity.ok(ApplicationErrorResponse.builder()
        .errorCode(exception.getError().getCode())
        .errorMsg(exception.getError().getI18nMsg())
        .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApplicationErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {
    List<FieldErrorDetail> fieldErrorDetails = exception.getFieldErrors()
        .stream()
        .map(errorDetail -> FieldErrorDetail.builder()
            .field(errorDetail.getField())
            .error(errorDetail.getDefaultMessage())
            .build())
        .toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationErrorResponse.builder()
        .errorCode(ApplicationError.VALIDATION_ERROR.getCode())
        .errorMsg(ApplicationError.VALIDATION_ERROR.getI18nMsg())
        .details(fieldErrorDetails)
        .build());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApplicationErrorResponse> handleUnexpectedException(RuntimeException exception) {
    log.error("unexpected error", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApplicationErrorResponse.builder()
        .errorCode(ApplicationError.UNEXPECTED_ERROR.getCode())
        .errorMsg(ApplicationError.UNEXPECTED_ERROR.getI18nMsg())
        .build());
  }
}

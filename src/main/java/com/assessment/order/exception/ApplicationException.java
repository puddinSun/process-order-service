package com.assessment.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ApplicationException extends RuntimeException {
  private final ApplicationError error;
}

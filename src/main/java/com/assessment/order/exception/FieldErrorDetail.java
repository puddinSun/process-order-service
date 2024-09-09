package com.assessment.order.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldErrorDetail {

  private String field;
  private String error;
}

package com.assessment.order.exception;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApplicationErrorResponse {

  private String errorCode;
  private String errorMsg;
  private List<FieldErrorDetail> details;
}

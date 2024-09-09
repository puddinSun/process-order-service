package com.assessment.order.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterCustomerResponse {
  private String customerId;
}

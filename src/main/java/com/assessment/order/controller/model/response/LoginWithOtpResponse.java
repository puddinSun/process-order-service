package com.assessment.order.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginWithOtpResponse {

  private String token;
}

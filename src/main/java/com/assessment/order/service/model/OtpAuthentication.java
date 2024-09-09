package com.assessment.order.service.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OtpAuthentication {

  private String otpRequestId;
  private String countryCode;
  private String mobileNumber;
  private String otp;
  private String token;

  public String getFullMobileNumber() {
    return countryCode + mobileNumber;
  }
}

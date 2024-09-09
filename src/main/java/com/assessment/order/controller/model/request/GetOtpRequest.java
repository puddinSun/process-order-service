package com.assessment.order.controller.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetOtpRequest {

  @NotNull
  @Size(max = 3)
  private String countryCode;

  @NotNull
  @Size(max = 15)
  private String mobileNumber;
}

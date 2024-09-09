package com.assessment.order.controller.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterCustomerRequest {

  @Size(max = 250)
  @NotNull
  private String customerName;

  @Size(max = 3)
  @NotNull
  private String countryCode;

  @Size(max = 12)
  @NotNull
  private String mobileNumber;

  @NotNull
  private String address;
}

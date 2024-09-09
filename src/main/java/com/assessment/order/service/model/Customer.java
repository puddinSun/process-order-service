package com.assessment.order.service.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Customer {

  private String customerId;
  private String customerName;
  private String countryCode;
  private String mobileNumber;
  private String address;
}

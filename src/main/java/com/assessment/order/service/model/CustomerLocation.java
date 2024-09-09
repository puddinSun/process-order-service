package com.assessment.order.service.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerLocation {

  private Double latitude;
  private Double longitude;
}

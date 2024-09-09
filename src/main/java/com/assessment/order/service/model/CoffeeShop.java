package com.assessment.order.service.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CoffeeShop {

  private String shopId;
  private String shopName;
  private String address;
  private Double latitude;
  private Double longitude;
}

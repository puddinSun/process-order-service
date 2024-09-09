package com.assessment.order.controller.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ViewNearbyShopResponse {

  private String shopId;
  private String shopName;
  private String address;
}

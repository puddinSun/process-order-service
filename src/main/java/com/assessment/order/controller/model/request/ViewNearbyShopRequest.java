package com.assessment.order.controller.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewNearbyShopRequest {

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  private Integer distanceInKm = 3;
}

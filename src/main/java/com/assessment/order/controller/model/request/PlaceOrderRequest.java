package com.assessment.order.controller.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceOrderRequest {

  @NotNull
  private String shopId;

  @Size(max = 255)
  private String notes;

  @NotEmpty
  private List<PlaceOrderItemRequest> orderItems;
}

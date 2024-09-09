package com.assessment.order.controller.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

  @NotNull
  @Size(max = 36)
  private String orderId;
}

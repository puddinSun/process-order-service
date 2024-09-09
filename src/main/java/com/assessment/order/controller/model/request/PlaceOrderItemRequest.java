package com.assessment.order.controller.model.request;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderItemRequest {

  @NotNull
  private String itemId;

  @NotNull
  private BigDecimal price;

  @NotNull
  private Integer quantity;
}

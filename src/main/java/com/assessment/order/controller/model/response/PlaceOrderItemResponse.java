package com.assessment.order.controller.model.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PlaceOrderItemResponse {

  private String menuName;
  private String itemName;
  private Integer quantity;
  private BigDecimal price;
}

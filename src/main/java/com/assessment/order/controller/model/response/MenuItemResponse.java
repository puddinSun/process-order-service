package com.assessment.order.controller.model.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MenuItemResponse {

  private String menuItemId;
  private String itemName;
  private String description;
  private BigDecimal price;
}

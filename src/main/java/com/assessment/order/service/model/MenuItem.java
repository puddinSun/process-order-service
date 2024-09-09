package com.assessment.order.service.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MenuItem {

  private String menuItemId;
  private String itemName;
  private String description;
  private BigDecimal price;
}

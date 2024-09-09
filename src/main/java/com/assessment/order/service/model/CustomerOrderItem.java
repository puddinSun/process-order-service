package com.assessment.order.service.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerOrderItem {

  private String itemId;
  private BigDecimal price;
  private Integer quantity;

  public UUID getItemUUID() {
    return UUID.fromString(itemId);
  }

  public BigDecimal getPriceByQuantity() {
    return price.multiply(BigDecimal.valueOf(quantity));
  }
}

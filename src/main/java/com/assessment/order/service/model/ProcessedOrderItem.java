package com.assessment.order.service.model;

import com.assessment.order.common.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProcessedOrderItem {

  private String orderItemId;
  private String menuName;
  private String itemId;
  private String itemName;
  private Integer quantity;
  private BigDecimal price;
  private OrderStatusEnum orderStatus;

  public BigDecimal getItemQuantityPrice() {
    return price.multiply(BigDecimal.valueOf(quantity));
  }

  public UUID getItemUUID() {
    return UUID.fromString(itemId);
  }
}

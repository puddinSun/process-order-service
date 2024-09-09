package com.assessment.order.service.model;

import com.assessment.order.common.enums.OrderStatusEnum;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerOrderStatus {

  private String orderId;
  private OrderStatusEnum orderStatus;

  public UUID getOrderUUID() {
    return UUID.fromString(orderId);
  }
}

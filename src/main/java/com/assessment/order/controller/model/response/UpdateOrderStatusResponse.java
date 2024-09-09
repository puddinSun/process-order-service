package com.assessment.order.controller.model.response;

import com.assessment.order.common.enums.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UpdateOrderStatusResponse {

  private String orderId;
  private OrderStatusEnum orderStatus;
}

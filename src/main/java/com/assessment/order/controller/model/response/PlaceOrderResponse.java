package com.assessment.order.controller.model.response;

import com.assessment.order.common.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlaceOrderResponse {

  private String orderId;
  private String shopName;
  private OrderStatusEnum orderStatus;
  private String queueName;
  private Integer queuePosition;
  private Integer expectedWaitTime;
  private BigDecimal totalPrice;
  private BigDecimal subTotal;
  private String notes;
  private List<PlaceOrderItemResponse> orderItems;
}

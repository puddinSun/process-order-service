package com.assessment.order.service.model;

import com.assessment.order.common.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProcessedOrder {

  private String orderId;
  private String shopName;
  private String queueName;
  private Integer queuePosition;
  private Integer expectedWaitTime; // in minutes
  private List<ProcessedOrderItem> orderItems;
  private BigDecimal totalPrice;
  private BigDecimal subTotal;
  private String notes;
  private OrderStatusEnum orderStatus;
}

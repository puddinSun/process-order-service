package com.assessment.order.repository.entity;

import com.assessment.order.common.enums.OrderStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer_order")
public class CustomerOrderEntity extends BaseEntity {

  @Column(name = "customer_id", length = 36, nullable = false)
  private UUID customerId;

  @Column(name = "queueId", length = 36, nullable = false)
  private UUID queueId;

  @Column(name = "queue_position", nullable = false)
  private Integer queuePosition;

  @Column(name = "total_price", nullable = false)
  private BigDecimal totalPrice;

  @Column(name = "sub_total", nullable = false)
  private BigDecimal subTotal;

  @Column(name = "voucher_code")
  private String voucherCode;

  @Column(name = "notes")
  private String notes;

  @Column(name = "order_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatusEnum orderStatus;
}

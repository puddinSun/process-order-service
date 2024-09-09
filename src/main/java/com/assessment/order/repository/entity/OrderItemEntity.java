package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "order_item")
public class OrderItemEntity extends BaseEntity {

  @Column(name = "order_id", length = 36, nullable = false)
  private UUID orderId;

  @Column(name = "menu_item_id", length = 36, nullable = false)
  private UUID menuItemId;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "price_snapshot", nullable = false)
  private BigDecimal priceSnapshot;
}

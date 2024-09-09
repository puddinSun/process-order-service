package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "queue_condition")
public class QueueConditionEntity extends BaseEntity {

  @Column(name = "queue_id", nullable = false)
  private UUID queueId;

  @Column(name = "order_id", nullable = false)
  private UUID orderId;
}

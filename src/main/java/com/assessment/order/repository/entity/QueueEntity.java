package com.assessment.order.repository.entity;

import com.assessment.order.common.constants.AppConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "queue")
public class QueueEntity extends BaseEntity {

  @Column(name = "shop_id", length = 36, nullable = false)
  private UUID shopId;

  @Column(name = "queue_name", nullable = false, length = 150)
  private String queueName;

  @Column(name = "capacity", nullable = false)
  private Integer capacity;

  @Override
  @PrePersist
  public void prePersist() {
    super.prePersist();
    if (capacity == null) {
      capacity = AppConstants.QUEUE_MAX_CAPACITY;
    }
  }
}

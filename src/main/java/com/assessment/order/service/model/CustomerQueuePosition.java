package com.assessment.order.service.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerQueuePosition {

  private UUID queueId;
  private String queueName;
  private Integer queuePosition;

  public Integer getExpectedWaitingTime() {
    return queuePosition * 3; // in minutes
  }
}

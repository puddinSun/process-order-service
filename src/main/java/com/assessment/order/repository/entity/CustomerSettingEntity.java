package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "customer_setting")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSettingEntity extends BaseEntity {

  @Column(name = "customer_id", nullable = false, length = 36, unique = true)
  private UUID customerId;

  @Column(name = "preferred_language", length = 3, nullable = false)
  private String preferredLanguage;

  @Column(name = "currency", length = 3, nullable = false)
  private String currency;

  @Column(name = "sms_enabled")
  private Boolean smsEnabled;
}

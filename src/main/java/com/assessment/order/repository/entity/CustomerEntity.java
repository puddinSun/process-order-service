package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
public class CustomerEntity extends BaseEntity {

  @Column(name = "customer_name", nullable = false, length = 150)
  private String customerName;

  @Column(name = "country_code", nullable = false, length = 3)
  private String countryCode;

  @Column(name = "mobile_number", nullable = false, length = 10)
  private String mobileNumber;

  @Column(name = "address")
  private String address;

  @Column(name = "device_id", length = 36)
  private String deviceId;

  @Column(name = "otp_pending", length = 6)
  private Boolean otpPending;

  // more: account_locked, time_to_unlock, login_error_count, etc
}

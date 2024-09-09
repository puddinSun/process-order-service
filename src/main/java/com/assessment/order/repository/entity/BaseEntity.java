package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @CreatedDate
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @Column(name = "active", nullable = false)
  private Boolean active;

  @PrePersist
  public void prePersist() {
    if (active == null) {
      active = Boolean.TRUE;
    }
  }
}

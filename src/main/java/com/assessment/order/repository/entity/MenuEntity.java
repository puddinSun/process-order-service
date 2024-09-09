package com.assessment.order.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "menu")
public class MenuEntity extends BaseEntity {

  @Column(name = "shop_id", length = 36, nullable = false)
  private UUID shopId;

  @Column(name = "menu_name", length = 150, nullable = false)
  private String menuName;
}

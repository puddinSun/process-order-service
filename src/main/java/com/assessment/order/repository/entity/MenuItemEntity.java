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
@Entity(name = "menu_item")
public class MenuItemEntity extends BaseEntity {

  @Column(name = "menu_id", nullable = false, length = 36)
  private UUID menuId;

  @Column(name = "item_name", nullable = false)
  private String itemName;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "price", nullable = false)
  private BigDecimal price;
}

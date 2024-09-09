package com.assessment.order.service.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerOrder {

  private String shopId;
  private String customerId;
  private String notes;
  private List<CustomerOrderItem> customerOrderItems;

  public UUID getShopUUID() {
    return UUID.fromString(shopId);
  }

  public UUID getCustomerUUID() {
    // TODO get the actual customer ID
    return customerId != null ? UUID.fromString(customerId) : UUID.randomUUID();
  }

  public BigDecimal getTotalPrice() {
    return customerOrderItems.stream().map(CustomerOrderItem::getPriceByQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

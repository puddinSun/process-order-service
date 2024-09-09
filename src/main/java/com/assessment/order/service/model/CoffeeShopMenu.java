package com.assessment.order.service.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CoffeeShopMenu {

  private String shopId;
  private String shopName;
  private String address;
  private List<Menu> menus;
}

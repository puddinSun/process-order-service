package com.assessment.order.service.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Menu {

  private String menuId;
  private String menuName;
  private List<MenuItem> menuItems;
}

package com.assessment.order.controller.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MenuResponse {

  private String menuId;
  private String menuName;
  private List<MenuItemResponse> menuItems;
}

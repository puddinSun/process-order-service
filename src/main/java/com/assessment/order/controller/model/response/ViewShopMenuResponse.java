package com.assessment.order.controller.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ViewShopMenuResponse {

  private String shopId;
  private String shopName;
  private List<MenuResponse> menuList;
}

package com.assessment.order.mapper;

import com.assessment.order.controller.model.request.ViewNearbyShopRequest;
import com.assessment.order.controller.model.response.ViewNearbyShopResponse;
import com.assessment.order.controller.model.response.ViewShopMenuResponse;
import com.assessment.order.repository.entity.CoffeeShopEntity;
import com.assessment.order.service.model.CoffeeShop;
import com.assessment.order.service.model.CoffeeShopMenu;
import com.assessment.order.service.model.CustomerLocation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface CoffeeShopMapper {

  CustomerLocation viewNearbyShopRequestToCustomerLocation(ViewNearbyShopRequest request);

  ViewNearbyShopResponse coffeeshopToViewNearbyShopResponse(CoffeeShop coffeeShop);

  @Mapping(target = "shopId", source = "id")
  CoffeeShop coffeeshopEntityToCoffeeShop(CoffeeShopEntity entity);

  @Mapping(target = "shopId", source = "id")
  CoffeeShopMenu coffeeShopEntityToCoffeeShopMenu(CoffeeShopEntity entity);

  ViewShopMenuResponse shopMenuToShopMenuResponse(CoffeeShopMenu coffeeShopMenu);
}

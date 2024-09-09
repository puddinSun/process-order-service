package com.assessment.order.controller;

import com.assessment.order.controller.model.request.ViewNearbyShopRequest;
import com.assessment.order.controller.model.response.MenuItemResponse;
import com.assessment.order.controller.model.response.MenuResponse;
import com.assessment.order.controller.model.response.ViewNearbyShopResponse;
import com.assessment.order.controller.model.response.ViewShopMenuResponse;
import com.assessment.order.mapper.CoffeeShopMapper;
import com.assessment.order.mapper.MenuItemMapper;
import com.assessment.order.mapper.MenuMapper;
import com.assessment.order.service.CoffeeShopService;
import com.assessment.order.service.model.CoffeeShopMenu;
import com.assessment.order.service.model.CustomerLocation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shops")
@RequiredArgsConstructor
public class CoffeeShopController {

  private final CoffeeShopService coffeeShopService;
  private final CoffeeShopMapper coffeeShopMapper;
  private final MenuMapper menuMapper;
  private final MenuItemMapper menuItemMapper;

  @PostMapping("/nearby")
  public ResponseEntity<List<ViewNearbyShopResponse>> viewNearbyShops(@RequestBody @Valid ViewNearbyShopRequest request) {
    CustomerLocation customerLocation = coffeeShopMapper.viewNearbyShopRequestToCustomerLocation(request);
    List<ViewNearbyShopResponse> nearbyShopList = coffeeShopService.viewNearbyShops(customerLocation, request.getDistanceInKm())
        .stream()
        .map(coffeeShopMapper::coffeeshopToViewNearbyShopResponse)
        .toList();

    return ResponseEntity.ok(nearbyShopList);
  }

  @GetMapping("/{shopId}/menus")
  public ResponseEntity<ViewShopMenuResponse> viewShopMenus(@PathVariable String shopId) {
    CoffeeShopMenu shopMenus = coffeeShopService.getShopMenus(shopId);

    ViewShopMenuResponse viewShopMenuResponse = coffeeShopMapper.shopMenuToShopMenuResponse(shopMenus);
    List<MenuResponse> menus = shopMenus.getMenus()
        .stream().map(menu -> {
          MenuResponse menuResponse = menuMapper.menuToMenuResponse(menu);
          List<MenuItemResponse> menuItemResponse = menu.getMenuItems().stream().map(menuItemMapper::menuItemToMenuItemResponse).toList();
          menuResponse.setMenuItems(menuItemResponse);
          return menuResponse;
        }).toList();

    viewShopMenuResponse.setMenuList(menus);
    return ResponseEntity.ok(viewShopMenuResponse);
  }

}

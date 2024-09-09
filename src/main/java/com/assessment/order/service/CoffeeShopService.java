package com.assessment.order.service;

import com.assessment.order.exception.ApplicationError;
import com.assessment.order.exception.ApplicationException;
import com.assessment.order.mapper.CoffeeShopMapper;
import com.assessment.order.mapper.MenuItemMapper;
import com.assessment.order.mapper.MenuMapper;
import com.assessment.order.repository.CoffeeShopRepository;
import com.assessment.order.repository.MenuItemRepository;
import com.assessment.order.repository.MenuRepository;
import com.assessment.order.service.model.CoffeeShop;
import com.assessment.order.service.model.CoffeeShopMenu;
import com.assessment.order.service.model.CustomerLocation;
import com.assessment.order.service.model.Menu;
import com.assessment.order.service.model.MenuItem;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeShopService {

  private final CoffeeShopRepository coffeeShopRepository;
  private final MenuRepository menuRepository;
  private final MenuItemRepository menuItemRepository;

  private final CoffeeShopMapper coffeeShopMapper;
  private final MenuMapper menuMapper;
  private final MenuItemMapper menuItemMapper;

  public List<CoffeeShop> viewNearbyShops(CustomerLocation customerLocation, Integer distanceInKm) {
    return coffeeShopRepository.findNearbyShopsAsc(customerLocation.getLatitude(), customerLocation.getLongitude(), distanceInKm)
        .stream()
        .map(coffeeShopMapper::coffeeshopEntityToCoffeeShop)
        .toList();
  }

  public CoffeeShopMenu getShopMenus(String shopId) {
    return coffeeShopRepository.findById(UUID.fromString(shopId)).map(shopEntity -> {
      CoffeeShopMenu shopMenu = coffeeShopMapper.coffeeShopEntityToCoffeeShopMenu(shopEntity);
      List<Menu> menus = menuRepository.findByShopIdAndActive(shopEntity.getId(), true)
          .stream()
          .map(menuEntity -> {
            Menu menu = menuMapper.menuEntityToMenu(menuEntity);
            List<MenuItem> menuItemList = menuItemRepository.findByMenuIdAndActive(menuEntity.getId(), true)
                .stream().map(menuItemMapper::menuItemEntityToMenuItem).toList();
            menu.setMenuItems(menuItemList);
            return menu;
          }).toList();
      shopMenu.setMenus(menus);
      return shopMenu;
    }).orElseThrow(() -> new ApplicationException(ApplicationError.SHOP_NOT_FOUND));
  }
}

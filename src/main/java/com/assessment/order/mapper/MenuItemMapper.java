package com.assessment.order.mapper;

import com.assessment.order.controller.model.response.MenuItemResponse;
import com.assessment.order.repository.entity.MenuItemEntity;
import com.assessment.order.service.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface MenuItemMapper {

  @Mapping(target = "menuItemId", source = "id")
  MenuItem menuItemEntityToMenuItem(MenuItemEntity entity);

  MenuItemResponse menuItemToMenuItemResponse(MenuItem menuItem);
}

package com.assessment.order.mapper;

import com.assessment.order.controller.model.response.MenuResponse;
import com.assessment.order.repository.entity.MenuEntity;
import com.assessment.order.service.model.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface MenuMapper {

  @Mapping(target = "menuId", source = "id")
  Menu menuEntityToMenu(MenuEntity entity);

  MenuResponse menuToMenuResponse(Menu menu);
}

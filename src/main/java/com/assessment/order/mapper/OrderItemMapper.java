package com.assessment.order.mapper;

import com.assessment.order.controller.model.request.PlaceOrderItemRequest;
import com.assessment.order.controller.model.response.PlaceOrderItemResponse;
import com.assessment.order.repository.entity.OrderItemEntity;
import com.assessment.order.service.model.CustomerOrderItem;
import com.assessment.order.service.model.ProcessedOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface OrderItemMapper {

  CustomerOrderItem orderItemRequestToCustomerOrder(PlaceOrderItemRequest request);

  PlaceOrderItemResponse processedOrderItemToPlaceOrderResponse(ProcessedOrderItem processedOrderItem);

  @Mapping(target = "price", source = "priceSnapshot")
  @Mapping(target = "orderItemId", source = "id")
  @Mapping(target = "itemId", source = "menuItemId")
  ProcessedOrderItem orderItemEntityToProcessedOrderItem(OrderItemEntity entity);
}

package com.assessment.order.mapper;

import com.assessment.order.controller.model.request.PlaceOrderRequest;
import com.assessment.order.controller.model.response.PlaceOrderResponse;
import com.assessment.order.controller.model.response.UpdateOrderStatusResponse;
import com.assessment.order.service.model.CustomerOrder;
import com.assessment.order.service.model.CustomerOrderStatus;
import com.assessment.order.service.model.ProcessedOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface OrderMapper {

  CustomerOrder placeOrderRequestToCustomerOrder(PlaceOrderRequest request, String customerId);

  @Mapping(target = "orderItems", ignore = true)
  PlaceOrderResponse processedOrderToPlaceOrderResponse(ProcessedOrder customerOrder);

  UpdateOrderStatusResponse customerOrderStatusToUpdateOrderResponse(CustomerOrderStatus orderStatus);
}

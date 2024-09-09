package com.assessment.order.controller;

import com.assessment.order.common.enums.OrderStatusEnum;
import com.assessment.order.controller.model.request.PlaceOrderRequest;
import com.assessment.order.controller.model.request.UpdateOrderStatusRequest;
import com.assessment.order.controller.model.response.PlaceOrderItemResponse;
import com.assessment.order.controller.model.response.PlaceOrderResponse;
import com.assessment.order.controller.model.response.UpdateOrderStatusResponse;
import com.assessment.order.mapper.OrderItemMapper;
import com.assessment.order.mapper.OrderMapper;
import com.assessment.order.service.OrderService;
import com.assessment.order.service.model.CustomerOrder;
import com.assessment.order.service.model.CustomerOrderItem;
import com.assessment.order.service.model.CustomerOrderStatus;
import com.assessment.order.service.model.ProcessedOrder;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final OrderMapper orderMapper;
  private final OrderItemMapper orderItemMapper;

  @PostMapping("/init")
  public ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody @Valid PlaceOrderRequest request) {
    // TODO get customer_id
    CustomerOrder customerOrder = orderMapper.placeOrderRequestToCustomerOrder(request, null);
    List<CustomerOrderItem> customerOrderItems = request.getOrderItems().stream().map(orderItemMapper::orderItemRequestToCustomerOrder).toList();
    customerOrder.setCustomerOrderItems(customerOrderItems);
    ProcessedOrder processedOrder = orderService.placeOrder(customerOrder);

    PlaceOrderResponse placeOrderResponse = orderMapper.processedOrderToPlaceOrderResponse(processedOrder);
    List<PlaceOrderItemResponse> placeOrderItemsResponse = processedOrder.getOrderItems()
        .stream()
        .map(orderItemMapper::processedOrderItemToPlaceOrderResponse)
        .toList();
    placeOrderResponse.setOrderItems(placeOrderItemsResponse);
    return ResponseEntity.status(HttpStatus.CREATED).body(placeOrderResponse);
  }

  @PostMapping("/cancel")
  public ResponseEntity<UpdateOrderStatusResponse> cancelOrder(@RequestBody @Valid UpdateOrderStatusRequest request) {
    CustomerOrderStatus customerOrderStatus = orderService.updateOrderStatus(CustomerOrderStatus.builder()
        .orderId(request.getOrderId())
        .orderStatus(OrderStatusEnum.CANCELED)
        .build());

    return ResponseEntity.ok(orderMapper.customerOrderStatusToUpdateOrderResponse(customerOrderStatus));
  }

  @PostMapping("/ready")
  public ResponseEntity<UpdateOrderStatusResponse> finishOrder(@RequestBody @Valid UpdateOrderStatusRequest request) {
    CustomerOrderStatus customerOrderStatus = orderService.updateOrderStatus(CustomerOrderStatus.builder()
        .orderId(request.getOrderId())
        .orderStatus(OrderStatusEnum.READY)
        .build());

    return ResponseEntity.ok(orderMapper.customerOrderStatusToUpdateOrderResponse(customerOrderStatus));
  }

  @PostMapping("/complete")
  public ResponseEntity<UpdateOrderStatusResponse> completeOrder(@RequestBody @Valid UpdateOrderStatusRequest request) {
    CustomerOrderStatus customerOrderStatus = orderService.updateOrderStatus(CustomerOrderStatus.builder()
        .orderId(request.getOrderId())
        .orderStatus(OrderStatusEnum.COMPLETED)
        .build());

    return ResponseEntity.ok(orderMapper.customerOrderStatusToUpdateOrderResponse(customerOrderStatus));
  }

}

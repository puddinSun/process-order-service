package com.assessment.order.service;

import com.assessment.order.common.enums.OrderStatusEnum;
import com.assessment.order.exception.ApplicationError;
import com.assessment.order.exception.ApplicationException;
import com.assessment.order.mapper.OrderItemMapper;
import com.assessment.order.repository.CoffeeShopRepository;
import com.assessment.order.repository.MenuItemRepository;
import com.assessment.order.repository.MenuRepository;
import com.assessment.order.repository.OrderItemRepository;
import com.assessment.order.repository.OrderRepository;
import com.assessment.order.repository.QueueConditionRepository;
import com.assessment.order.repository.QueueRepository;
import com.assessment.order.repository.entity.CoffeeShopEntity;
import com.assessment.order.repository.entity.CustomerOrderEntity;
import com.assessment.order.repository.entity.OrderItemEntity;
import com.assessment.order.repository.entity.QueueConditionEntity;
import com.assessment.order.repository.entity.QueueEntity;
import com.assessment.order.service.model.CustomerOrder;
import com.assessment.order.service.model.CustomerOrderStatus;
import com.assessment.order.service.model.CustomerQueuePosition;
import com.assessment.order.service.model.ProcessedOrder;
import com.assessment.order.service.model.ProcessedOrderItem;
import com.assessment.order.service.validator.OrderValidatorService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderValidatorService orderValidatorService;
  private final QueueRepository queueRepository;
  private final QueueConditionRepository queueConditionRepository;
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final MenuItemRepository menuItemRepository;
  private final MenuRepository menuRepository;
  private final CoffeeShopRepository coffeeShopRepository;

  private final OrderItemMapper orderItemMapper;

  @Transactional
  public ProcessedOrder placeOrder(CustomerOrder customerOrder) {
    log.info("message=\"Start to process order, shopId={}\"", customerOrder.getShopId());
    orderValidatorService.validateOrder(customerOrder);
    // 1. Get available queue and next position

    CustomerQueuePosition customerQueuePosition = getCustomerPosition(customerOrder.getShopUUID());

    // 2. Init order
    CustomerOrderEntity newOrder = orderRepository.save(CustomerOrderEntity.builder()
        .customerId(customerOrder.getCustomerUUID())
        .orderStatus(OrderStatusEnum.PENDING)
        .queueId(customerQueuePosition.getQueueId())
        .queuePosition(customerQueuePosition.getQueuePosition())
        .subTotal(customerOrder.getTotalPrice())
        .totalPrice(customerOrder.getTotalPrice())
        .notes(customerOrder.getNotes())
        .build());

    // 3. Save order items
    List<OrderItemEntity> orderItemEntities = customerOrder.getCustomerOrderItems().stream()
        .map(customerOrderItem -> OrderItemEntity.builder()
            .orderId(newOrder.getId())
            .menuItemId(customerOrderItem.getItemUUID())
            .quantity(customerOrderItem.getQuantity())
            .priceSnapshot(customerOrderItem.getPrice())
            .build())
        .toList();
    orderItemRepository.saveAll(orderItemEntities);

    // 4. Add user to queue
    queueConditionRepository.save(QueueConditionEntity.builder()
        .orderId(newOrder.getId())
        .queueId(newOrder.getQueueId())
        .build());
    newOrder.setOrderStatus(OrderStatusEnum.IN_QUEUE);
    orderRepository.save(newOrder);

    // 5. Build response
    List<ProcessedOrderItem> processedItems = orderItemEntities.stream()
        .map(orderItemMapper::orderItemEntityToProcessedOrderItem)
        .toList();

    processedItems.forEach(processedItem ->
        menuItemRepository.findById(processedItem.getItemUUID()).ifPresent(menuItemEntity -> {
          processedItem.setItemName(menuItemEntity.getItemName());
          menuRepository.findById(menuItemEntity.getMenuId()).ifPresent(menuEntity ->
              processedItem.setMenuName(menuEntity.getMenuName())
          );
        }));

    CoffeeShopEntity shopEntity = coffeeShopRepository.findById(customerOrder.getShopUUID())
        .orElseThrow(() -> new ApplicationException(ApplicationError.SHOP_NOT_FOUND));

    ProcessedOrder processedOrder = ProcessedOrder.builder()
        .orderId(newOrder.getId().toString())
        .shopName(shopEntity.getShopName())
        .queuePosition(customerQueuePosition.getQueuePosition())
        .orderStatus(OrderStatusEnum.IN_QUEUE)
        .notes(newOrder.getNotes())
        .queueName(customerQueuePosition.getQueueName())
        .totalPrice(newOrder.getTotalPrice())
        .subTotal(newOrder.getSubTotal())
        .queueName(customerQueuePosition.getQueueName())
        .expectedWaitTime(customerQueuePosition.getExpectedWaitingTime())
        .orderItems(processedItems)
        .build();

    log.info("message=\"Order has been placed by customer\"");
    return processedOrder;
  }

  private CustomerQueuePosition getCustomerPosition(UUID shopId) {
    int minPersonInQueue = 0;
    int index = 0;
    QueueEntity availableQueue = null;

    List<QueueEntity> allQueueInShop = queueRepository.findByShopIdAndActive(shopId, true);
    for (QueueEntity queueEntity : allQueueInShop) {
      List<QueueConditionEntity> numberOfPersonInQueue = queueConditionRepository.findByQueueId(queueEntity.getId());
      if (index == 0) {
        minPersonInQueue = numberOfPersonInQueue.size();
        availableQueue = queueEntity;
      }
      if (numberOfPersonInQueue.size() < minPersonInQueue && numberOfPersonInQueue.size() < queueEntity.getCapacity()) {
        minPersonInQueue = numberOfPersonInQueue.size();
        availableQueue = queueEntity;
      }
      index++;
    }

    if (availableQueue == null) {
      throw new ApplicationException(ApplicationError.NO_QUEUE_AVAILABLE);
    }

    return CustomerQueuePosition.builder()
        .queueId(availableQueue.getId())
        .queueName(availableQueue.getQueueName())
        .queuePosition(minPersonInQueue + 1)
        .build();
  }

  public CustomerOrderStatus updateOrderStatus(CustomerOrderStatus customerOrderStatus) {
    CustomerOrderEntity customerOrder = orderRepository.findById(customerOrderStatus.getOrderUUID())
        .orElseThrow(() -> new ApplicationException(ApplicationError.ORDER_NOT_FOUND));

    switch (customerOrderStatus.getOrderStatus()) {
      case CANCELED -> {
        if (OrderStatusEnum.READY.equals(customerOrder.getOrderStatus()) || OrderStatusEnum.COMPLETED.equals(customerOrder.getOrderStatus())) {
          throw new ApplicationException(ApplicationError.CANNOT_CANCEL_ORDER);
        }
        customerOrder.setOrderStatus(OrderStatusEnum.CANCELED);
        orderRepository.save(customerOrder);
        queueConditionRepository.findByOrderId(customerOrder.getId()).ifPresent(queueConditionRepository::delete);
      }
      case READY -> {
        if (OrderStatusEnum.CANCELED.equals(customerOrder.getOrderStatus()) || OrderStatusEnum.COMPLETED.equals(customerOrder.getOrderStatus())) {
          throw new ApplicationException(ApplicationError.ORDER_NOT_IN_QUEUE);
        }
        customerOrder.setOrderStatus(OrderStatusEnum.READY);
        orderRepository.save(customerOrder);
      }
      case COMPLETED -> {
        if (OrderStatusEnum.COMPLETED.equals(customerOrder.getOrderStatus())) {
          throw new ApplicationException(ApplicationError.ORDER_ALREADY_COMPLETED);
        }
        if (!OrderStatusEnum.READY.equals(customerOrder.getOrderStatus())) {
          throw new ApplicationException(ApplicationError.ORDER_NOT_READY);
        }
        customerOrder.setOrderStatus(OrderStatusEnum.COMPLETED);
        orderRepository.save(customerOrder);
        queueConditionRepository.findByOrderId(customerOrder.getId()).ifPresent(queueConditionRepository::delete);
      }
      default -> {
      }
    }

    return customerOrderStatus;
  }
}

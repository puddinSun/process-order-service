package com.assessment.order.service.validator;

import com.assessment.order.exception.ApplicationError;
import com.assessment.order.exception.ApplicationException;
import com.assessment.order.repository.MenuItemRepository;
import com.assessment.order.repository.QueueConditionRepository;
import com.assessment.order.repository.QueueRepository;
import com.assessment.order.repository.entity.BaseEntity;
import com.assessment.order.repository.entity.QueueConditionEntity;
import com.assessment.order.repository.entity.QueueEntity;
import com.assessment.order.service.model.CustomerOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderValidatorService {

  private final QueueRepository queueRepository;
  private final QueueConditionRepository queueConditionRepository;
  private final MenuItemRepository menuItemRepository;

  public void validateOrder(CustomerOrder order) {
    log.info("message=\"Start to validate order before processing, customerId={}\"", order.getCustomerId());

    // 1. check queue availability
    Map<UUID, QueueEntity> queueMap = queueRepository.findByShopIdAndActive(order.getShopUUID(), true)
        .stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));

    List<QueueConditionEntity> currentNumberOfCustomerInQueue = new ArrayList<>();
    int totalCapacity = 0;
    for (Entry<UUID, QueueEntity> entry : queueMap.entrySet()) {
      QueueEntity queueEntity = entry.getValue();
      totalCapacity += queueEntity.getCapacity();
      log.info("message=\"queue {} has capacity {} \"", queueEntity.getQueueName(), queueEntity.getCapacity());
      currentNumberOfCustomerInQueue.addAll(queueConditionRepository.findByQueueId(entry.getKey()));
    }

    if (currentNumberOfCustomerInQueue.size() >= totalCapacity) {
      throw new ApplicationException(ApplicationError.ALL_QUEUE_ARE_FULL);
    }

    // 2. validate order item
    order.getCustomerOrderItems().forEach(orderItem -> {
      if (!menuItemRepository.existsByIdAndActive(orderItem.getItemUUID(), true)) {
        throw new ApplicationException(ApplicationError.ITEM_NOT_FOUND);
      }
    });
  }
}


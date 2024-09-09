package com.assessment.order.repository;

import com.assessment.order.repository.entity.QueueConditionEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueConditionRepository extends JpaRepository<QueueConditionEntity, UUID> {

  List<QueueConditionEntity> findByQueueId(UUID queueId);

  Optional<QueueConditionEntity> findByOrderId(UUID orderId);
}

package com.assessment.order.repository;

import com.assessment.order.repository.entity.QueueEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity, UUID> {

  List<QueueEntity> findByShopIdAndActive(UUID shopId, boolean active);
}

package com.assessment.order.repository;

import com.assessment.order.repository.entity.CustomerOrderEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrderEntity, UUID> {}

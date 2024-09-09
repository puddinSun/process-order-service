package com.assessment.order.repository;

import com.assessment.order.repository.entity.CustomerSettingEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerSettingRepository extends JpaRepository<CustomerSettingEntity, UUID> {}

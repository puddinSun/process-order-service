package com.assessment.order.repository;

import com.assessment.order.repository.entity.MenuEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {

  List<MenuEntity> findByShopIdAndActive(UUID shopId, boolean active);
}

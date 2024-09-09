package com.assessment.order.repository;

import com.assessment.order.repository.entity.MenuItemEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, UUID> {

  List<MenuItemEntity> findByMenuIdAndActive(UUID menuId, boolean active);

  boolean existsByIdAndActive(UUID menuItemId, boolean active);
}

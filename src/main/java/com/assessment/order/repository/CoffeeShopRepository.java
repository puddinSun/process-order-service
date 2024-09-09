package com.assessment.order.repository;

import com.assessment.order.repository.entity.CoffeeShopEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShopEntity, UUID> {

  // simple and quick solution
  String HAVERSINE_FORMULA_QUERY = """
      WITH
          current_location AS (SELECT :lat1 AS lat1, :lon1 AS lon1)
      SELECT
          s.*,
          6371 * 2 * ASIN(SQRT(
                  POWER(SIN((RADIANS(latitude) - RADIANS(c.lat1)) / 2), 2) +
                  COS(RADIANS(c.lat1)) * COS(RADIANS(latitude)) *
                  POWER(SIN((RADIANS(longitude) - RADIANS(c.lon1)) / 2), 2)
          )) AS distance
      FROM
          coffeeshop s, current_location c
      WHERE
          6371 * 2 * ASIN(SQRT(
              POWER(SIN((RADIANS(latitude) - RADIANS(c.lat1)) / 2), 2) +
              COS(RADIANS(c.lat1)) * COS(RADIANS(latitude)) *
              POWER(SIN((RADIANS(longitude) - RADIANS(c.lon1)) / 2), 2)
          )) <= :distance
      ORDER by distance ASC;
      """;

  @Query(value = HAVERSINE_FORMULA_QUERY, nativeQuery = true)
  List<CoffeeShopEntity> findNearbyShopsAsc(
      @Param("lat1") Double customerLat, @Param("lon1") Double customerLon, @Param("distance") Integer distance);
}

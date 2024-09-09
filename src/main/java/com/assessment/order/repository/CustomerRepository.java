package com.assessment.order.repository;

import com.assessment.order.repository.entity.CustomerEntity;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

  boolean existsByCountryCodeAndMobileNumber(String countryCode, String mobileNumber);

  Optional<CustomerEntity> findByCountryCodeAndMobileNumberAndActive(String countryCode, String mobileNumber, boolean active);
}

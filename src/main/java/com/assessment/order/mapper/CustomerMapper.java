package com.assessment.order.mapper;

import com.assessment.order.controller.model.request.RegisterCustomerRequest;
import com.assessment.order.repository.entity.CustomerEntity;
import com.assessment.order.service.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface CustomerMapper {

  Customer registerRequestToCustomer(RegisterCustomerRequest dto);

  CustomerEntity customerToCustomerEntity(Customer customer);

  @Mapping(target = "customerId", source = "id")
  Customer customerEntityToCustomer(CustomerEntity entity);
}

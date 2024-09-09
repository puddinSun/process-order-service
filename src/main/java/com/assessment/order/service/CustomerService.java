package com.assessment.order.service;

import com.assessment.order.exception.ApplicationError;
import com.assessment.order.exception.ApplicationException;
import com.assessment.order.mapper.CustomerMapper;
import com.assessment.order.repository.CustomerRepository;
import com.assessment.order.repository.entity.CustomerEntity;
import com.assessment.order.service.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public Customer registerCustomer(Customer customer) {
    if (customerRepository.existsByCountryCodeAndMobileNumber(customer.getCountryCode(), customer.getMobileNumber())) {
      throw new ApplicationException(ApplicationError.CUSTOMER_ALREADY_REGISTERED);
    }

    CustomerEntity customerEntity = customerMapper.customerToCustomerEntity(customer);
    return customerMapper.customerEntityToCustomer(customerRepository.save(customerEntity));
  }
}

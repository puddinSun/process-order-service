package com.assessment.order.controller;

import com.assessment.order.controller.model.request.RegisterCustomerRequest;
import com.assessment.order.controller.model.response.RegisterCustomerResponse;
import com.assessment.order.mapper.CustomerMapper;
import com.assessment.order.service.CustomerService;
import com.assessment.order.service.model.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @PostMapping
  public ResponseEntity<RegisterCustomerResponse> registerCustomer(@RequestBody @Valid RegisterCustomerRequest request) {
    Customer customer = customerService.registerCustomer(customerMapper.registerRequestToCustomer(request));
    return ResponseEntity.status(HttpStatus.CREATED).body(RegisterCustomerResponse.builder()
        .customerId(customer.getCustomerId())
        .build());
  }
}


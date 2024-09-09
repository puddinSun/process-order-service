package com.assessment.order.service;

import com.assessment.order.exception.ApplicationError;
import com.assessment.order.exception.ApplicationException;
import com.assessment.order.infa.infobip.InfobipOtpClient;
import com.assessment.order.infa.infobip.InfobipSendOtpRequest;
import com.assessment.order.repository.CustomerRepository;
import com.assessment.order.repository.entity.CustomerEntity;
import com.assessment.order.service.model.OtpAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final InfobipOtpClient infobipOtpClient;
  private final CustomerRepository customerRepository;
  private final JwtTokenService jwtTokenService;

  public OtpAuthentication requestOtp(OtpAuthentication otpAuthentication) {
    // get and validate customer
    CustomerEntity customerEntity = customerRepository.findByCountryCodeAndMobileNumberAndActive(otpAuthentication.getCountryCode(), otpAuthentication.getMobileNumber(), true)
        .orElseThrow(() -> new ApplicationException(ApplicationError.CUSTOMER_NOT_FOUND));

    // call api to send OTP
    String requestID = infobipOtpClient.sendOtp(InfobipSendOtpRequest.builder()
        .msg("Your OTP is 111111")
        .to(otpAuthentication.getFullMobileNumber())
        .senderName("SYSTEM")
        .build());

    // update OTP pending request
    customerEntity.setOtpPending(true);
    customerRepository.save(customerEntity);

    return OtpAuthentication.builder().otpRequestId(requestID).build();
  }

  public OtpAuthentication loginWithOtp(OtpAuthentication otpAuthentication) {
    // get and validate customer
    CustomerEntity customerEntity = customerRepository.findByCountryCodeAndMobileNumberAndActive(otpAuthentication.getCountryCode(), otpAuthentication.getMobileNumber(), true)
        .orElseThrow(() -> new ApplicationException(ApplicationError.CUSTOMER_NOT_FOUND));

    // validate customer otp status
    if (Boolean.FALSE.equals(customerEntity.getOtpPending()) || null == customerEntity.getOtpPending()) {
      throw new ApplicationException(ApplicationError.OTP_INVALID);
    }

    // call api to validate OTP
    boolean success = infobipOtpClient.verifyOtp(otpAuthentication.getOtp());
    if (!success) {
      throw new ApplicationException(ApplicationError.CUSTOMER_ALREADY_REGISTERED);
    }

    // generate jwt token
    String jwtToken = jwtTokenService.generateJwtRS256(otpAuthentication.getFullMobileNumber());
    return OtpAuthentication.builder()
        .token(jwtToken)
        .build();
  }

}

package com.assessment.order.controller;

import com.assessment.order.controller.model.request.GetOtpRequest;
import com.assessment.order.controller.model.request.LoginWithOtpRequest;
import com.assessment.order.controller.model.response.GetOtpResponse;
import com.assessment.order.controller.model.response.LoginWithOtpResponse;
import com.assessment.order.mapper.AuthenticationMapper;
import com.assessment.order.service.AuthenticationService;
import com.assessment.order.service.model.OtpAuthentication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final AuthenticationMapper authenticationMapper;

  @PostMapping("/otp/retrieve")
  public ResponseEntity<GetOtpResponse> requestOtp(@RequestBody @Valid GetOtpRequest request) {
    OtpAuthentication otpAuthentication = authenticationService.requestOtp(authenticationMapper.getOtpRequestToLoginOtp(request));
    
    return ResponseEntity.ok(GetOtpResponse.builder()
        .otpRequestId(otpAuthentication.getOtpRequestId())
        .build());
  }

  @PostMapping("/otp/login")
  public ResponseEntity<LoginWithOtpResponse> loginWithOtp(@RequestBody @Valid LoginWithOtpRequest request) {
    OtpAuthentication otpAuthentication =
        authenticationService.loginWithOtp(authenticationMapper.loginWithOtpRequestToOtpAuthentication(request));

    return ResponseEntity.ok(LoginWithOtpResponse.builder()
        .token(otpAuthentication.getToken())
        .build());
  }

}

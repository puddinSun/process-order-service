package com.assessment.order.infa.infobip;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InfobipOtpClient {

  public String sendOtp(InfobipSendOtpRequest request) {
    log.info("message=\"Start to send OTP via infobip, mobile={}\"", request.getTo());
    // mock response
    return UUID.randomUUID().toString();
  }

  public boolean verifyOtp(String otp) {
    log.info("message=\"Start to verify OTP, otp={}\"", otp);
    // mock response
    return "111111".equals(otp);
  }

}

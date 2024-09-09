package com.assessment.order.mapper;

import com.assessment.order.controller.model.request.GetOtpRequest;
import com.assessment.order.controller.model.request.LoginWithOtpRequest;
import com.assessment.order.service.model.OtpAuthentication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = GlobalMapperConfig.class)
public interface AuthenticationMapper {

  OtpAuthentication getOtpRequestToLoginOtp(GetOtpRequest request);
  OtpAuthentication loginWithOtpRequestToOtpAuthentication(LoginWithOtpRequest request);
}

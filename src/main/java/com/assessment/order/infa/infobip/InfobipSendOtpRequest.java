package com.assessment.order.infa.infobip;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InfobipSendOtpRequest {

  private String senderName;
  private String to;
  private String msg;
}

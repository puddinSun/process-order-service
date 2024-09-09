package com.assessment.order.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.auth")
@Component
public class AuthenticationProperties {

  private String jwtEncKey;
  private String jwtExpInMills;
}

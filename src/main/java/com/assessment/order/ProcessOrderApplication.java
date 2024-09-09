package com.assessment.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.assessment.order.repository")
@EnableJpaAuditing
public class ProcessOrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProcessOrderApplication.class);
  }
}

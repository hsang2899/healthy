package com.healthy.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.healthy"})
@EnableJpaRepositories(basePackages = "com.healthy")
@EntityScan("com.healthy.entity")
@SpringBootApplication
public class HealthyApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(HealthyApplication.class, args);
  }
  
  
}

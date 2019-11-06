/*
 * @author Sysmex
 *
 * Copyright (c) 2019 Sysmex America, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sysmex America, Inc.
 * ("Confidential Information").
 *
 */
package com.healthy.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Class Configuration for Data Access Layout
 * 
 * @author LuongTN
 * @date 2019-08-01
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaAuditing
public class DataConfiguration {
  /**
   * Bean for mapper object to other object
   * 
   * @return
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}

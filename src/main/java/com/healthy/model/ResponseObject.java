/*
 * @author Sysmex
 *
 * Copyright (c) 2019 Sysmex America, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sysmex America, Inc.
 * ("Confidential Information").
 *
 */
package com.healthy.model;

import java.io.IOException;
import java.util.Properties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.log4j.Log4j2;

/**
 * Class DTO format standard for all response.
 * 
 * @author LuongTN
 * @date 2019-08-01
 * @version 1.0
 * @param <T>
 */
@Log4j2
public class ResponseObject<T> {

  // Status of response
  private boolean success;

  // Message of response
  private String message;

  // Body data of response
  private T data;

  // Error code of response
  @JsonInclude(value = Include.NON_NULL)
  @JsonProperty("error_code")
  private String errorCode = null;

  /**
   * Contructor of reponse succesfully
   * 
   * @param body Data to response
   * @param success Status of response
   * @param message Message to response
   */
  public ResponseObject(T body, boolean success, String message) {
    this.data = body;
    this.success = success;
    this.message = message;
  }

  /**
   * Contructor of reponse succesfully with no data to response
   * 
   * @param success Status of reponse
   * @param message Message to reponse
   */
  public ResponseObject(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  /**
   * Contructor of reponse unsuccesfully
   * 
   * @param body Data of response
   * @param errorCode Error code of response
   */
  public ResponseObject(T body, String errorCode) {
    this.data = body;
    this.errorCode = errorCode;
    getProperties();
  }

  /**
   * Contructor of reponse succesfully with no data to response
   * 
   * @param errorCode Error code of response
   */
  public ResponseObject(String errorCode) {
    this.errorCode = errorCode;
    getProperties();
  }

  /**
   * Get properties from file for message
   * 
   * @return Properties of message
   */
  private Properties getProperties() {

    Properties prop = new Properties();
    try {
      // Load a properties file from class path, inside static method
      prop.load(ResponseObject.class.getClassLoader().getResourceAsStream("messages.properties"));
      // Get the property value and print it out
      this.message = prop.getProperty(errorCode);
    } catch (IOException ex) {
      log.error(ex);
    }

    return prop;
  }

  /**
   * @return the success
   */
  public boolean isSuccess() {
    return success;
  }

  /**
   * @param success the success to set
   */
  public void setSuccess(boolean success) {
    this.success = success;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the data
   */
  public T getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * @return the errorCode
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @param errorCode the errorCode to set
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

}

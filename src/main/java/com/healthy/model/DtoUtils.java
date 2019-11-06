package com.healthy.model;

import org.modelmapper.ModelMapper;

public class DtoUtils {

  private DtoUtils() {

  }

  // Convert Object Entity to DTO mapped by properties name and type data
  public static DTOEntity convertToDto(Object objData, DTOEntity mapperTarget) {
    return new ModelMapper().map(objData, mapperTarget.getClass());
  }

  // Convert Object DTO to Entity mapped by properties name and type data
  public static Object convertToEntity(Object objTarget, DTOEntity mapperData) {
    return new ModelMapper().map(mapperData, objTarget.getClass());
  }

}

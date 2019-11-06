package com.healthy.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {
  private static final long serialVersionUID = -5418336033708665513L;

  @JsonProperty(value = "en")
  private String message;

}

package com.healthy.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthy.model.NotifySendDto.NotifySendDtoBuilder;
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
public class NotifyDto implements DTOEntity, Serializable {
  private static final long serialVersionUID = -1521029272162147195L;

  @JsonProperty(value = "email")
  private String email;
  @JsonProperty(value = "title")
  private String title;
  @JsonProperty(value = "message")
  private String message;

}

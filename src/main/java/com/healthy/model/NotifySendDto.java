package com.healthy.model;

import java.io.Serializable;
import java.util.List;
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
public class NotifySendDto implements DTOEntity, Serializable {
  private static final long serialVersionUID = -2430160023693360449L;
  @JsonProperty(value = "app_id")
  private String appId;
  @JsonProperty(value = "include_player_ids")
  private List<Long> includePlayerIds;
  @JsonProperty(value = "headings")
  private MessageDto headings;
  @JsonProperty(value = "contents")
  private MessageDto contents;
}

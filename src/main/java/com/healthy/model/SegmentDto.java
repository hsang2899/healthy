package com.healthy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SegmentDto implements DTOEntity {
    @JsonProperty(value = "player_id")
    private Long playerId;

    @JsonProperty(value = "email")
    private String email;
}

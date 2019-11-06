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
public class GoalDto implements DTOEntity {
    @JsonProperty(value = "calories")
    private double calories;

    @JsonProperty(value = "proteins")
    private double proteins;

    @JsonProperty(value = "carbon")
    private double carbon;

    @JsonProperty(value = "fat")
    private double fat;

    @JsonProperty(value = "weight_start")
    private double weightStart;

    @JsonProperty(value = "weight")
    private double weight;

    @JsonProperty(value = "weight_finish")
    private double weightFinish;

    @JsonProperty(value = "month")
    private Integer month;

    @JsonProperty(value = "type")
    private Integer type;

    @JsonProperty(value = "activity_level")
    private Integer activityLevel; // 1, 2, 3
}

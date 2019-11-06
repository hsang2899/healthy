package com.healthy.model;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthy.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutritionDto implements DTOEntity {
  @JsonProperty(value = "nutrition_id")
  private Long nutritionId;

  @JsonProperty(value = "user_profile_id")
  private UserProfile userProfile;

  @JsonProperty(value = "weights")
  private List<WeightDto> weights;

  @JsonProperty(value = "heights")
  private List<HeightDto> heights;

  @JsonProperty(value = "weight_target")
  private Float weightTarget;

  @JsonProperty(value = "activity_level")
  private Integer activityLevel;

  @JsonProperty(value = "month_target")
  private Integer monthTarget;

  @JsonProperty(value = "started_date")
  private Date startedDate;
}

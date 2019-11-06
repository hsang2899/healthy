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
public class FoodDto implements DTOEntity {
  @JsonProperty(value = "food_id")
  private Long foodId;

  @JsonProperty(value = "food_name")
  private String foodName;

  @JsonProperty(value = "description")
  private String description;

  @JsonProperty(value = "unit")
  private String unit;

  @JsonProperty(value = "calories")
  private Double calories;

  @JsonProperty(value = "proteins")
  private Double proteins;

  @JsonProperty(value = "carbs")
  private Double carbs;

  @JsonProperty(value = "fat")
  private Double fat;

  @JsonProperty(value = "quantity")
  private Integer quantity;
}

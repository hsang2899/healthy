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
public class MealCreateDto implements DTOEntity{
  @JsonProperty(value = "food_id")
  private Long foodId;

  @JsonProperty(value = "quantity")
  private Integer quantity;
}

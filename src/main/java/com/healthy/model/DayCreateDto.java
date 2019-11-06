package com.healthy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayCreateDto implements DTOEntity{
  @JsonProperty(value = "account_id")
  private Long accountId;

  @JsonProperty(value = "date")
  private Long date;

  @JsonProperty(value = "break_fast")
  private List<MealCreateDto> breakFast;

  @JsonProperty(value = "lunch")
  private List<MealCreateDto> lunch;

  @JsonProperty(value = "dinner")
  private List<MealCreateDto> dinner;

  @JsonProperty(value = "snacks")
  private List<MealCreateDto> snacks;
  
  @JsonProperty(value = "exercises")
  private List<ExerciseDayCreateDto> exercises;
}

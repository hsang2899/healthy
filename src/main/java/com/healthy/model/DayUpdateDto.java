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
public class DayUpdateDto implements DTOEntity {
	@JsonProperty(value = "meals")
	private List<MealCreateDto> meals;
	@JsonProperty(value = "exercises")
	private List<ExerciseDayCreateDto> exercises;
	@JsonProperty(value = "type")
	private String type;
}

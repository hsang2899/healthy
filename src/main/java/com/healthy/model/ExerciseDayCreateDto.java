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
public class ExerciseDayCreateDto implements DTOEntity {
	@JsonProperty(value = "exercise_id")
	private Long exerciseId;
}

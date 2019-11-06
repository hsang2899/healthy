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
public class ExerciseDayDto implements DTOEntity {
	@JsonProperty(value = "exercise_day_id")
	private Long exerciseDayId;
	
	@JsonProperty(value = "day_id")
	private Long dayId;
	
	@JsonProperty(value = "exercise_details")
	private List<ExerciseDetailDto> exerciseDetails;
}

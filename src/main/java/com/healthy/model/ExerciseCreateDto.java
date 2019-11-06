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
public class ExerciseCreateDto implements DTOEntity {
	
	@JsonProperty(value = "index_of_sets")
	private Integer indexOfSets;

	@JsonProperty(value = "repetitions")
	private Integer repetitions;

	@JsonProperty(value = "weight_per_set")
	private Double weightPerSet;

	@JsonProperty(value = "calories_burn")
	private Double caloriesBurn;

	@JsonProperty(value = "exercise_name")
	private String exerciseName;

	@JsonProperty(value = "video_url")
	private String videoUrl;
}

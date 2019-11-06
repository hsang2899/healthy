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
public class WeightDto implements DTOEntity {
	@JsonProperty(value = "weight_id")
	private Long weightId;

	@JsonProperty(value = "weight")
	private Float weight;

	@JsonProperty(value = "date")
	private Long date;
}

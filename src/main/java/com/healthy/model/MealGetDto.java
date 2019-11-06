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
public class MealGetDto implements DTOEntity {
	@JsonProperty(value = "meal_id")
	private Long mealId;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "foods")
	private List<FoodMealGetDto> foods;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MealGetDto other = (MealGetDto) obj;
		if (mealId == null) {
			if (other.mealId != null)
				return false;
		} else if (!mealId.equals(other.mealId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mealId == null) ? 0 : mealId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	
	
}

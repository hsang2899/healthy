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
public class DayGetDto implements DTOEntity {
	@JsonProperty(value = "day_id")
	private Long dayId;

	@JsonProperty(value = "date")
	private Long date;

	@JsonProperty(value = "account_id")
	private Long accountId;

	@JsonProperty(value = "break_fast")
	private MealGetDto breakFast;

	@JsonProperty(value = "lunch")
	private MealGetDto lunch;

	@JsonProperty(value = "dinner")
	private MealGetDto dinner;

	@JsonProperty(value = "snacks")
	private MealGetDto snacks;
	
	@JsonProperty(value = "exercise")
	private ExerciseDayDto exercise;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayGetDto other = (DayGetDto) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (dayId == null) {
			if (other.dayId != null)
				return false;
		} else if (!dayId.equals(other.dayId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((dayId == null) ? 0 : dayId.hashCode());
		return result;
	}
	
	
}

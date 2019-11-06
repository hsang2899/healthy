package com.healthy.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.healthy.entity.Day;
import com.healthy.entity.ExerciseDetail;
import com.healthy.entity.MealDetail;
import com.healthy.model.DayGetDto;
import com.healthy.model.ExerciseDayDto;
import com.healthy.model.ExerciseDetailDto;
import com.healthy.model.FoodMealGetDto;
import com.healthy.model.MealGetDto;
import com.healthy.repository.DayRepository;
import com.healthy.repository.ExerciseDetailRepository;
import com.healthy.repository.MealDetailRepository;
import com.healthy.service.DayService;

@Service
public class DayServiceImpl implements DayService {
	@Autowired
	DayRepository dayRepository;

	@Autowired
	ExerciseDetailRepository exerciseDetailRepository;

	@Autowired
	MealDetailRepository mealDetailRepository;

	@Override
	public Day createDay(Day day) {
		return dayRepository.save(day);
	}

	@Override
	public List<DayGetDto> getAllDay(Long accountId) {
		List<Map<String, String>> dayBreakFastResults = dayRepository.getAllDayWithBreakFast(accountId);
		List<Map<String, String>> dayLunchResults = dayRepository.getAllDayWithLunch(accountId);
		List<Map<String, String>> dayDinnerResults = dayRepository.getAllDayWithDinner(accountId);
		List<Map<String, String>> daySnacksResults = dayRepository.getAllDayWithSnacks(accountId);
		List<Map<String, String>> dayExercisesResults = dayRepository.getAllDayWithExercise(accountId);
		return convertMapToDayDto(dayBreakFastResults, dayLunchResults, dayDinnerResults, daySnacksResults,
				dayExercisesResults);
	}

	@Override
	public DayGetDto getDayByDate(Long accountId, Long date) {
		List<Map<String, String>> dayBreakFastResults = dayRepository.getADayWithBreakFast(accountId, date);
		List<Map<String, String>> dayLunchResults = dayRepository.getADayWithLunch(accountId, date);
		List<Map<String, String>> dayDinnerResults = dayRepository.getADayWithDinner(accountId, date);
		List<Map<String, String>> daySnacksResults = dayRepository.getADayWithSnacks(accountId, date);
		List<Map<String, String>> dayExercisesResults = dayRepository.getADayWithExercise(accountId, date);
		List<DayGetDto> listDayDto = convertMapToDayDto(dayBreakFastResults, dayLunchResults, dayDinnerResults,
				daySnacksResults, dayExercisesResults);
		if (!CollectionUtils.isEmpty(listDayDto)) {
			return listDayDto.get(0);
		}
		return null;
	}

	private List<DayGetDto> convertMapToDayDto(List<Map<String, String>> dayBreakFastResults,
			List<Map<String, String>> dayLunchResults, List<Map<String, String>> dayDinnerResults,
			List<Map<String, String>> daySnacksResults, List<Map<String, String>> dayExercisesResults) {
		Map<DayGetDto, Map<MealGetDto, List<FoodMealGetDto>>> dayWithMealMap = new LinkedHashMap<>();
		Map<DayGetDto, Map<ExerciseDayDto, List<ExerciseDetailDto>>> dayWithExerciseMap = new LinkedHashMap<>();
		Map<DayGetDto, DayGetDto> mappingDayGetDto = new LinkedHashMap<>();

		for (Map<String, String> map : dayBreakFastResults) {
			DayGetDto day = generateDayDto(map);
			MealGetDto meal = generateMealDto(map);
			FoodMealGetDto food = generateFoodDto(map);

			Map<MealGetDto, List<FoodMealGetDto>> mealWithFoodlMap = dayWithMealMap.containsKey(day)
					? dayWithMealMap.get(day)
					: new LinkedHashMap<>();
			List<FoodMealGetDto> foodList = mealWithFoodlMap.containsKey(meal) ? mealWithFoodlMap.get(meal)
					: new ArrayList<>();
			foodList.add(food);
			meal.setFoods(foodList);
			mealWithFoodlMap.put(meal, foodList);
			day.setBreakFast(meal);
			dayWithMealMap.put(day, mealWithFoodlMap);
			DayGetDto dayDto = mappingDayGetDto.containsKey(day) ? mappingDayGetDto.get(day) : day;
			dayDto.setBreakFast(meal);
			mappingDayGetDto.put(day, dayDto);
		}

		for (Map<String, String> map : dayLunchResults) {
			DayGetDto day = generateDayDto(map);
			MealGetDto meal = generateMealDto(map);
			FoodMealGetDto food = generateFoodDto(map);

			Map<MealGetDto, List<FoodMealGetDto>> mealWithFoodlMap = dayWithMealMap.containsKey(day)
					? dayWithMealMap.get(day)
					: new LinkedHashMap<>();
			List<FoodMealGetDto> foodList = mealWithFoodlMap.containsKey(meal) ? mealWithFoodlMap.get(meal)
					: new ArrayList<>();
			foodList.add(food);
			meal.setFoods(foodList);
			mealWithFoodlMap.put(meal, foodList);
			day.setLunch(meal);
			dayWithMealMap.put(day, mealWithFoodlMap);
			DayGetDto dayDto = mappingDayGetDto.containsKey(day) ? mappingDayGetDto.get(day) : day;
			dayDto.setLunch(meal);
			mappingDayGetDto.put(day, dayDto);
		}

		for (Map<String, String> map : dayDinnerResults) {
			DayGetDto day = generateDayDto(map);
			MealGetDto meal = generateMealDto(map);
			FoodMealGetDto food = generateFoodDto(map);

			Map<MealGetDto, List<FoodMealGetDto>> mealWithFoodlMap = dayWithMealMap.containsKey(day)
					? dayWithMealMap.get(day)
					: new LinkedHashMap<>();
			List<FoodMealGetDto> foodList = mealWithFoodlMap.containsKey(meal) ? mealWithFoodlMap.get(meal)
					: new ArrayList<>();
			foodList.add(food);
			meal.setFoods(foodList);
			mealWithFoodlMap.put(meal, foodList);
			day.setDinner(meal);
			dayWithMealMap.put(day, mealWithFoodlMap);
			DayGetDto dayDto = mappingDayGetDto.containsKey(day) ? mappingDayGetDto.get(day) : day;
			dayDto.setDinner(meal);
			mappingDayGetDto.put(day, dayDto);
		}

		for (Map<String, String> map : daySnacksResults) {
			DayGetDto day = generateDayDto(map);
			MealGetDto meal = generateMealDto(map);
			FoodMealGetDto food = generateFoodDto(map);

			Map<MealGetDto, List<FoodMealGetDto>> mealWithFoodlMap = dayWithMealMap.containsKey(day)
					? dayWithMealMap.get(day)
					: new LinkedHashMap<>();
			List<FoodMealGetDto> foodList = mealWithFoodlMap.containsKey(meal) ? mealWithFoodlMap.get(meal)
					: new ArrayList<>();
			foodList.add(food);
			meal.setFoods(foodList);
			mealWithFoodlMap.put(meal, foodList);
			day.setSnacks(meal);
			dayWithMealMap.put(day, mealWithFoodlMap);
			DayGetDto dayDto = mappingDayGetDto.containsKey(day) ? mappingDayGetDto.get(day) : day;
			dayDto.setSnacks(meal);
			mappingDayGetDto.put(day, dayDto);
		}

		for (Map<String, String> map : dayExercisesResults) {
			DayGetDto day = generateDayDto(map);
			ExerciseDayDto exerciseDay = generateExericseDayDto(map);
			ExerciseDetailDto exercise = generateExerciseDetailDto(map);

			Map<ExerciseDayDto, List<ExerciseDetailDto>> exerciseWithMap = dayWithExerciseMap.containsKey(day)
					? dayWithExerciseMap.get(day)
					: new LinkedHashMap<>();
			List<ExerciseDetailDto> exerciseList = exerciseWithMap.containsKey(exerciseDay)
					? exerciseWithMap.get(exerciseDay)
					: new ArrayList<>();
			exerciseList.add(exercise);
			exerciseDay.setExerciseDetails(exerciseList);
			;
			exerciseWithMap.put(exerciseDay, exerciseList);
			day.setExercise(exerciseDay);
			dayWithExerciseMap.put(day, exerciseWithMap);
			DayGetDto dayDto = mappingDayGetDto.containsKey(day) ? mappingDayGetDto.get(day) : day;
			dayDto.setExercise(exerciseDay);
			mappingDayGetDto.put(day, dayDto);
		}
		List<DayGetDto> dayDtoList = new ArrayList<>();
		for (DayGetDto day : mappingDayGetDto.keySet()) {
			dayDtoList.add(mappingDayGetDto.get(day));
		}
		return dayDtoList;
	}

	private FoodMealGetDto generateFoodDto(Map<String, String> map) {
		return FoodMealGetDto.builder().calories(Double.parseDouble(map.get("calories")))
				.carbs(Double.parseDouble(map.get("carbs"))).description(map.get("description"))
				.fat(Double.parseDouble(map.get("fat"))).foodId(Long.parseLong(map.get("food_id")))
				.foodName(map.get("food_name")).proteins(Double.parseDouble(map.get("proteins")))
				.mealQuantity(Integer.parseInt(map.get("meal_quantity")))
				.quantity(Integer.parseInt(map.get("quantity"))).unit(map.get("unit")).mealDetailId(Long.parseLong(map.get("meal_detail_id"))).build();
	}

	private MealGetDto generateMealDto(Map<String, String> map) {
		return MealGetDto.builder().mealId(Long.parseLong(map.get("meal_id"))).status(map.get("status")).build();
	}

	private DayGetDto generateDayDto(Map<String, String> map) {
		return DayGetDto.builder().accountId(Long.parseLong(map.get("account_id")))
				.date(Long.parseLong(map.get("date"))).dayId(Long.parseLong(map.get("day_id"))).build();
	}

	private ExerciseDayDto generateExericseDayDto(Map<String, String> map) {
		return ExerciseDayDto.builder().exerciseDayId(Long.parseLong(map.get("exercise_day_id"))).build();
	}

	private ExerciseDetailDto generateExerciseDetailDto(Map<String, String> map) {
		return ExerciseDetailDto.builder().caloriesBurn(Double.parseDouble(map.get("calories_burn")))
				.exerciseId(Long.parseLong(map.get("exercise_id")))
				.indexOfSets(Integer.parseInt(map.get("index_of_sets")))
				.repetitions(Integer.parseInt(map.get("repetitions"))).exerciseName(map.get("exercise_name"))
				.videoUrl(map.get("video_url")).weightPerSet(Double.parseDouble(map.get("weight_per_set"))).exerciseDetailId(Long.parseLong(map.get("exercise_detail_id"))).build();
	}

	@Override
	public Day getById(Long accountId, Long dayId) {
		return dayRepository.findByDayIdAndAccountAccountId(dayId, accountId);
	}

	@Override
	public ExerciseDetail deleteExerciseDetail(Long exerciseDetailId) {
		ExerciseDetail exerciseDetail = exerciseDetailRepository.findByExerciseDetailId(exerciseDetailId);
		exerciseDetailRepository.delete(exerciseDetail);
		return exerciseDetail;
	}

	@Override
	public MealDetail deleteMealDetail(Long mealDetailId) {
		MealDetail mealDetail = mealDetailRepository.findByMealDetailId(mealDetailId);
		mealDetailRepository.delete(mealDetail);
		return mealDetail;
	}

	@Override
	public Day findByDate(Long date,Long accountId) {
		return dayRepository.findByDateAndAccountAccountId(date, accountId);
	}

	@Override
	public MealDetail getMealDetailById(Long mealDetailId) {
		return mealDetailRepository.findByMealDetailId(mealDetailId);
	}

	@Override
	public MealDetail saveMealDetail(MealDetail mealDetail) {
		return mealDetailRepository.save(mealDetail);
	}
}

package com.healthy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthy.entity.Account;
import com.healthy.entity.Day;
import com.healthy.entity.Exercise;
import com.healthy.entity.ExerciseDay;
import com.healthy.entity.ExerciseDetail;
import com.healthy.entity.Food;
import com.healthy.entity.Meal;
import com.healthy.entity.MealDetail;
import com.healthy.model.DTOEntity;
import com.healthy.model.DayCreateDto;
import com.healthy.model.DayGetDto;
import com.healthy.model.DayUpdateDto;
import com.healthy.model.DtoUtils;
import com.healthy.model.ExerciseDayCreateDto;
import com.healthy.model.MealCreateDto;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.service.DayService;
import com.healthy.service.ExerciseService;
import com.healthy.service.MealService;

@RestController(value = "MenuController")
@RequestMapping("/api")
public class MenuController {
	@Autowired
	private DayService dayService;

	@Autowired
	private MealService mealService;
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ExerciseService exerciseService;

//	@PostMapping(value = "/menu/create")
//	public ResponseObject<DTOEntity> createDay(@RequestBody DayCreateDto dayCreateDto , HttpServletRequest request) {
//		String rsEmail = request.getAttribute("rsEmail").toString();
//		Account account = accountRepository.findByEmail(rsEmail);
//		Meal breakFast = mealService.createMeal(Meal.builder().status("New").build());
//		Meal lunch = mealService.createMeal(Meal.builder().status("New").build());
//		Meal dinner = mealService.createMeal(Meal.builder().status("New").build());
//		Meal snacks = mealService.createMeal(Meal.builder().status("New").build());
//		ExerciseDay exerciseDay = exerciseService.saveDay(ExerciseDay.builder().status("New").build());
//		// (Meal) DtoUtils.convertToEntity(new Meal(), dayCreateDto.getSnacks());
//
//		generateListMealDetail(dayCreateDto.getBreakFast(), breakFast.getMealId());
//		generateListMealDetail(dayCreateDto.getLunch(), lunch.getMealId());
//		generateListMealDetail(dayCreateDto.getDinner(), dinner.getMealId());
//		generateListMealDetail(dayCreateDto.getSnacks(), snacks.getMealId());
//		generateListExerciseDetail(dayCreateDto.getExercises(), exerciseDay.getExerciseDayId());
//
//		Day day = new Day();
//		day.setDate(dayCreateDto.getDate());
//		day.setAccount(Account.builder().accountId(account.getAccountId()).build());
//		day.setBreakFast(breakFast);
//		day.setDinner(lunch);
//		day.setLunch(dinner);
//		day.setSnacks(snacks);
//		day.setExerciseDay(exerciseDay);
//		DTOEntity newDay = DtoUtils.convertToDto(dayService.createDay(day), new DayCreateDto());
//
//		return new ResponseObject<>(newDay, true, "Create new day successfully.");
//	}
	
	private Day generateNewDay(Long accountId,Long date) {
		Meal breakFast = mealService.createMeal(Meal.builder().status("New").build());
		Meal lunch = mealService.createMeal(Meal.builder().status("New").build());
		Meal dinner = mealService.createMeal(Meal.builder().status("New").build());
		Meal snacks = mealService.createMeal(Meal.builder().status("New").build());
		ExerciseDay exerciseDay = exerciseService.saveDay(ExerciseDay.builder().status("New").build());
		Day day = new Day();
		day.setDate(date);
		day.setAccount(Account.builder().accountId(accountId).build());
		day.setBreakFast(breakFast);
		day.setDinner(lunch);
		day.setLunch(dinner);
		day.setSnacks(snacks);
		day.setExerciseDay(exerciseDay);
		return dayService.createDay(day);
	}
	
	@PutMapping(value = "/menu/{date}")
	public ResponseObject<DTOEntity> createDay(@RequestBody DayUpdateDto dayCreateDto,@PathVariable(name = "date") Long date , HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		Day day = dayService.findByDate(date, account.getAccountId());
		if(null == day) {
			day = generateNewDay(account.getAccountId(),date);
			
		}
		if(null != dayCreateDto.getMeals() && !dayCreateDto.getMeals().isEmpty()) {
			String type = dayCreateDto.getType();
			switch(type) {
			case "breakfast":
				generateListMealDetail(dayCreateDto.getMeals(), day.getBreakFast().getMealId());
				break;
			case "lunch":
				generateListMealDetail(dayCreateDto.getMeals(), day.getLunch().getMealId());
				break;
			case "dinner":
				generateListMealDetail(dayCreateDto.getMeals(), day.getDinner().getMealId());
				break;
			case "snacks":
				generateListMealDetail(dayCreateDto.getMeals(), day.getSnacks().getMealId());
				break;
			}
		}
		if(null != dayCreateDto.getExercises() && !dayCreateDto.getExercises().isEmpty()) {
			generateListExerciseDetail(dayCreateDto.getExercises(),
					day.getExerciseDay().getExerciseDayId());
		}
		return new ResponseObject<>(null, true, "Create update day successfully.");
	}

	private List<MealDetail> generateListMealDetail(List<MealCreateDto> mealDetails, Long mealId) {
		List<MealDetail> listMealDetail = new ArrayList<>();
		for (MealCreateDto mealDetail : mealDetails) {
			listMealDetail.add(MealDetail.builder().food(Food.builder().foodId(mealDetail.getFoodId()).build())
					.meal(Meal.builder().mealId(mealId).build()).quantity(mealDetail.getQuantity()).build());
		}
		return mealService.createMealDetailList(listMealDetail);
	}

	private List<ExerciseDetail> generateListExerciseDetail(List<ExerciseDayCreateDto> exerciseDetails,
			Long exerciseDayId) {
		if (exerciseDetails == null) {
			return null;
		} else {
			List<ExerciseDetail> listExerciseDetail = new ArrayList<>();
			for (ExerciseDayCreateDto exerciseDetail : exerciseDetails) {
				listExerciseDetail.add(
						ExerciseDetail.builder().exerciseDay(ExerciseDay.builder().exerciseDayId(exerciseDayId).build())
								.exercise(Exercise.builder().exerciseId(exerciseDetail.getExerciseId()).build()).build());
			}
			return exerciseService.saveDetailList(listExerciseDetail);
		}
	}

	@GetMapping("/menu/getAll")
	public ResponseObject<List<DayGetDto>> getDay(HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Logger logger = Logger.getLogger(UserProfileController.class.getName());
		logger.info("rsEmail: " + rsEmail);
		Account account = accountRepository.findByEmail(rsEmail);
		return new ResponseObject<>(dayService.getAllDay(account.getAccountId()), true, "Create new day successfully.");
	}
	
	@GetMapping("/menu/getSingle")
	public ResponseObject<DayGetDto> getSingleDay(HttpServletRequest request,@RequestParam("date") Long date) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		
		return new ResponseObject<>(dayService.getDayByDate(account.getAccountId(), date), true, "Get single day successfully.");
	}
	
	@DeleteMapping("/menu/exercise/{exerciseDetailId}")
	public ResponseObject<DTOEntity> deleteExerciseOfMenu(HttpServletRequest request,@PathVariable("exerciseDetailId") Long exerciseDetailId) {
		dayService.deleteExerciseDetail(exerciseDetailId);
		return new ResponseObject<>(null, true, "Delete exercise of menu successfully.");
	}
	
	@DeleteMapping("/menu/food/{mealDetailId}")
	public ResponseObject<DTOEntity> deleteFoodOfMenu(HttpServletRequest request,@PathVariable("mealDetailId") Long mealDetailId) {
		dayService.deleteMealDetail(mealDetailId);
		return new ResponseObject<>(null, true, "Delete food of menu successfully.");
	}
	
	@PutMapping("/menu/meal/{mealDetailId}")
	public ResponseObject<DTOEntity> updateQuantityFoodOfMenu(HttpServletRequest request,@PathVariable("mealDetailId") Long mealDetailId,@RequestParam("quantity") Integer quantity) {
		MealDetail mealDetail = dayService.getMealDetailById(mealDetailId);
		mealDetail.setQuantity(quantity);
		dayService.saveMealDetail(mealDetail);
		return new ResponseObject<>(null, true, "Save quantity food of menu successfully.");
	}
}

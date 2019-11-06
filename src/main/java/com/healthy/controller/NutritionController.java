package com.healthy.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.healthy.api.NutritionApi;
import com.healthy.config.BMR_TDEE;
import com.healthy.entity.Account;
import com.healthy.entity.Goal;
import com.healthy.entity.UserProfile;
import com.healthy.entity.Weight;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.GoalDto;
import com.healthy.model.ResponseObject;
import com.healthy.model.WeightDto;
import com.healthy.repository.AccountRepository;
import com.healthy.repository.GoalRepository;
import com.healthy.repository.UserProfileRepository;
import com.healthy.service.GoalService;
import com.healthy.service.NutritionService;

@RestController("NutritionController")

public class NutritionController {
	Logger logger = Logger.getLogger(NutritionController.class.getName());

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserProfileRepository userProfileRepo;

	@Autowired
	NutritionService nutritionService;

	@Autowired
	GoalService goalService;

	@Autowired
	GoalRepository goalRepository;

	@PostMapping(value = "/api/nutrition")
	@ResponseBody
	public NutritionApi.Response nutri(@RequestBody NutritionApi.Request req, HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		logger.info("rsEmail: " + rsEmail);
		Account account = accountRepository.findByEmail(rsEmail);
		UserProfile userProfile = userProfileRepo.findByAccountAccountId(account.getAccountId());
		NutritionApi.Response res = new NutritionApi.Response();
		if (account == null || userProfile == null) {
			return null;
		}

		String gender = userProfile.getGender();
		Long bthDay = userProfile.getDateOfBirth();
		Float weightUser = userProfile.getWeight();
		Float heightUser = userProfile.getHeight();
		logger.info("weightUser: " + weightUser);

		double wStart = userProfile.getWeight();
		double w = req.getWeight();
		Integer level = req.getActivityLevel();
		Integer month = req.getMonth();
		Integer type = req.getType();
		double wFinish = wStart + w * type;

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(bthDay);
		int mYear = c.get(Calendar.YEAR);
		c.setTimeInMillis(System.currentTimeMillis());
		int thisYear = c.get(Calendar.YEAR);
		int oldUser = thisYear - mYear;
		logger.info("old user: " + oldUser);

		double BMR = 0.0;
		double activytiLevel = 0.0;
		if (gender.equals("male")) {
			logger.info("with boy");
			if (oldUser >= 18 && oldUser < 30) {
				BMR = BMR_TDEE.getBRMBOY_1830(weightUser);
			}
			if (oldUser >= 30 && oldUser < 60) {
				BMR = BMR_TDEE.getBMRBOY_3060(weightUser);
			}
			// CHECK LEVEL BOY
			if (level == 1) {
				activytiLevel = BMR_TDEE.activity_level1BOY;
			}
			if (level == 2) {
				activytiLevel = BMR_TDEE.activity_level2BOY;
			}
			if (level == 3) {
				activytiLevel = BMR_TDEE.activity_level3BOY;
			}
			if (level == 4) {
				activytiLevel = BMR_TDEE.activity_level4BOY;
			}
		} else {
			logger.info("with girl");
			if (oldUser >= 18 && oldUser < 30) {
				BMR = BMR_TDEE.getBMRGIRL_1830(weightUser);
			}
			if (oldUser >= 30 && oldUser < 60) {
				BMR = BMR_TDEE.getBMRGIRL_3060(weightUser);
			}
			// CHECK LEVEL GIRL
			if (level == 1) {
				activytiLevel = BMR_TDEE.activity_level1girl;
			}
			if (level == 2) {
				activytiLevel = BMR_TDEE.activity_level2GIRL;
			}
			if (level == 3) {
				activytiLevel = BMR_TDEE.activity_level3GIRL;
			}
			if (level == 4) {
				activytiLevel = BMR_TDEE.activity_level4GIRL;
			}
		}
		logger.info("BMR: " + BMR);
		logger.info("activytiLevel: " + activytiLevel);
		double TDEE = BMR_TDEE.getTDEE(BMR, activytiLevel);
		logger.info("TDEE: " + TDEE);
		double KALOEDIT = (w * 7700 * type) / (30 * month);
		logger.info("KALOEDIT: " + KALOEDIT);
		double KALOGOAL = TDEE + KALOEDIT;
		logger.info("KALOGOAL: " + KALOGOAL);
		double pro = (KALOGOAL * BMR_TDEE.macroPercentProtein) / 4;
		double carb = (KALOGOAL * BMR_TDEE.macroPercentCarb) / 4;
		double fat = (KALOGOAL * BMR_TDEE.macroPercentFat) / 4;

		GoalDto goalDto = new GoalDto();
		goalDto.setCalories(KALOGOAL);
		goalDto.setWeightStart(wStart);
		goalDto.setWeightFinish(wFinish);
		goalDto.setWeight(w);
		goalDto.setMonth(month);
		goalDto.setProteins(pro);
		goalDto.setCarbon(carb);
		goalDto.setFat(fat);
		goalDto.setActivityLevel(level);
		goalDto.setType(type);

		Goal goal = (Goal) DtoUtils.convertToEntity(new Goal(), goalDto);
		goal.setEmail(rsEmail);
		goal.setAccount(account);
		goal.setCreatedAt(System.currentTimeMillis());
		goal.setUpdatedAt(System.currentTimeMillis());

		DTOEntity newGoal = DtoUtils.convertToDto(goalService.createGoal(goal), new GoalDto());

		NutritionApi.Response rest = new NutritionApi.Response();
		rest.setCalories(KALOGOAL);
		rest.setWeight(w);
		rest.setWeightStart(wStart);
		rest.setWeightFinish(wFinish);
		rest.setMonth(month);
		rest.setProteins(pro);
		rest.setCarbon(carb);
		rest.setFat(fat);
		rest.setType(type);
		rest.setActivityLevel(level);
		return rest;
	}

	@GetMapping(value = "/api/my-goal")
	@ResponseBody
	public NutritionApi.Response myGoal(HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		logger.info("rsEmail: " + rsEmail);
		Goal goal = goalRepository.findByEmail(rsEmail);
		logger.info("rsgoal: " + goal.getActivityLevel());
		NutritionApi.Response rest = new NutritionApi.Response();
		rest.setCalories(goal.getCalories());
		rest.setWeight(goal.getWeight());
		rest.setMonth(goal.getMonth());
		rest.setProteins(goal.getProteins());
		rest.setCarbon(goal.getCarbon());
		rest.setFat(goal.getFat());
		rest.setActivityLevel(goal.getActivityLevel());
		rest.setWeightStart(goal.getWeightStart());
		rest.setWeightFinish(goal.getWeightFinish());
		rest.setType(goal.getType());
		logger.info("rsgoal: " + rest.getActivityLevel());
		return rest;
	}

	@GetMapping(value = "/api/all-goals")
	@ResponseBody
	public ResponseObject<List<DTOEntity>> goalAll(HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		if (account == null) {
			logger.info("account null,error forbidden!");
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
		}
		if (!account.getRole().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
		}
		List<DTOEntity> list = goalService.searchGoal().stream()
				.map(entity -> DtoUtils.convertToDto(entity, new GoalDto())).collect(Collectors.toList());
		return new ResponseObject<>(list, true, "Get list goal successfully.");
	}

	@PostMapping(value = "/api/weight")
	public ResponseObject<DTOEntity> createWeight(@RequestBody WeightDto weightDto, HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		Weight weight = nutritionService.findByDate(weightDto.getDate(),account.getAccountId());
		if (null == weight) {
			weight = Weight.builder().account(account).date(weightDto.getDate()).weight(weightDto.getWeight())
					.weightId(null).build();
		} else {
			weight.setWeight(weightDto.getWeight());
		}
		DTOEntity newWeight = DtoUtils.convertToDto(nutritionService.saveWeight(weight), new WeightDto());
		return new ResponseObject<>(newWeight, true, "Save weight successfully.");
	}

	@GetMapping(value = "/api/weights")
	@ResponseBody
	public ResponseObject<List<DTOEntity>> getAllWeights(@RequestParam("startDate") Long startDate,
			@RequestParam("endDate") Long endDate, HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		List<DTOEntity> listWeight = nutritionService.findWeightByPeriodTime(startDate, endDate, account.getAccountId())
				.stream().map(entity -> DtoUtils.convertToDto(entity, new WeightDto())).collect(Collectors.toList());
		return new ResponseObject<>(listWeight, true, "Get list weight successfully.");
	}

	@DeleteMapping("/api/weights/{date}")
	public ResponseObject<DTOEntity> deleteWeight(@PathVariable("date") Long date, HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		nutritionService.deleteWeightByDate(date,account.getAccountId());
		return new ResponseObject<>(null, true, "Delete weight successfully.");
	}
}

package com.healthy.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.healthy.entity.Account;
import com.healthy.entity.Exercise;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.ExerciseCreateDto;
import com.healthy.model.ExerciseDetailDto;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.service.ExerciseService;
import org.springframework.web.server.ResponseStatusException;

@RestController(value = "ExerciseController")
@RequestMapping("/api/exercises")
public class ExerciseController {
	Logger logger = Logger.getLogger(ExerciseController.class.getName());
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ExerciseService exerciseService;

	@PostMapping(value = "/create")
	public ResponseObject<DTOEntity> createExercise(@RequestBody ExerciseCreateDto exerciseDto,
			HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		Exercise exercise = (Exercise) DtoUtils.convertToEntity(new Exercise(), exerciseDto);
		logger.info("check role user add food: " + account.getRole());
		if (!account.getRole().equals("user")) {
			exercise.setStatus(false);
			exercise.setOwner("customer");
		} else {
			exercise.setStatus(true);
			exercise.setOwner("admin");
		}
		exercise.setAccount(account);
		exercise.setCreatedAt(System.currentTimeMillis());
		exercise.setUpdatedAt(System.currentTimeMillis());
		DTOEntity newExercise = DtoUtils.convertToDto(exerciseService.save(exercise), new ExerciseCreateDto());
		return new ResponseObject<>(newExercise, true, "Create exercise successfully.");
	}

	@PutMapping(value = "/update")
	public ResponseObject<DTOEntity> updateExercise(@RequestBody ExerciseDetailDto exerciseDto) {
		Exercise oldExercise = exerciseService.getById(exerciseDto.getExerciseId());
		Exercise exercise = (Exercise) DtoUtils.convertToEntity(new Exercise(), exerciseDto);
		exercise.setAccount(oldExercise.getAccount());
		exercise.setStatus(false);
		exercise.setOwner("customer");
		DTOEntity newExercise = DtoUtils.convertToDto(exerciseService.save(exercise), new ExerciseDetailDto());
		return new ResponseObject<>(newExercise, true, "Update exercise successfully.");
	}

	@GetMapping(value = "/getList")
	public ResponseObject<List<DTOEntity>> listExercise(HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		List<DTOEntity> listExercise = exerciseService.getAll(account.getAccountId()).stream()
				.map(entity -> DtoUtils.convertToDto(entity, new ExerciseDetailDto())).collect(Collectors.toList());
		return new ResponseObject<>(listExercise, true, "Get list exercise successfully.");
	}

	@GetMapping(value = "/all")
	@ResponseBody
	public ResponseObject<List<DTOEntity>> exerciseAll(HttpServletRequest request) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		if (account == null) {
			logger.info("account null,error forbidden!");
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
		}
		if (!account.getRole().equals("admin")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
		}
		List<DTOEntity> list = exerciseService.all().stream()
				.map(entity -> DtoUtils.convertToDto(entity, new ExerciseDetailDto())).collect(Collectors.toList());
		return new ResponseObject<>(list, true, "Get list exercise successfully.");
	}

	@PutMapping(value = "/active")
	public ResponseObject<DTOEntity> activeExercise(@RequestBody ExerciseDetailDto exerciseDto) {
		Exercise oldExercise = exerciseService.getById(exerciseDto.getExerciseId());
		Exercise exercise = (Exercise) DtoUtils.convertToEntity(new Exercise(), exerciseDto);
		exercise.setAccount(oldExercise.getAccount());
		exercise.setStatus(true);
		DTOEntity newExercise = DtoUtils.convertToDto(exerciseService.save(exercise), new ExerciseDetailDto());
		return new ResponseObject<>(newExercise, true, "Update status exercise successfully.");
	}
	
	@GetMapping(value = "/{exerciseId}")
	public ResponseObject<DTOEntity> getSingleExercise(@PathVariable("exerciseId") Long exerciseId) {
		Exercise exercise = exerciseService.getById(exerciseId);
		return new ResponseObject<>(DtoUtils.convertToDto(exerciseService.save(exercise), new ExerciseDetailDto()), true, "Get exercise successfully.");
	}
	
	@DeleteMapping(value = "/{exerciseId}")
	public ResponseObject<DTOEntity> deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
		exerciseService.deleteById(exerciseId);
		return new ResponseObject<>(null, true, "Delete exercise successfully.");
	}
	
	@GetMapping(value = "/")
	public ResponseObject<List<DTOEntity>> searchExercise(HttpServletRequest request,@RequestParam("exerciseName") String exerciseName) {
		String rsEmail = request.getAttribute("rsEmail").toString();
		Account account = accountRepository.findByEmail(rsEmail);
		List<DTOEntity> listExercise = exerciseService.searchExercise(exerciseName,account.getAccountId()).stream()
				.map(entity -> DtoUtils.convertToDto(entity, new ExerciseDetailDto())).collect(Collectors.toList());
		return new ResponseObject<>(listExercise, true, "Get list exercise successfully.");
	}
}

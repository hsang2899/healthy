package com.healthy.controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.healthy.entity.Account;
import com.healthy.entity.Food;
import com.healthy.model.DTOEntity;
import com.healthy.model.DtoUtils;
import com.healthy.model.FoodDto;
import com.healthy.model.ResponseObject;
import com.healthy.repository.AccountRepository;
import com.healthy.service.FoodService;
import org.springframework.web.server.ResponseStatusException;

@RestController(value = "FoodController")
@RequestMapping("/api/foods")
public class FoodController {
  Logger logger = Logger.getLogger(FoodController.class.getName());
  @Autowired
  FoodService foodService;
  @Autowired
  AccountRepository accountRepository;

  @PostMapping(value = "/create")
  public ResponseObject<DTOEntity> createFood(@RequestBody FoodDto foodDto, HttpServletRequest request) {
    String rsEmail = request.getAttribute("rsEmail").toString();
    Account account = accountRepository.findByEmail(rsEmail);
    Food food = (Food) DtoUtils.convertToEntity(new Food(), foodDto);
    logger.info("check role user add food: " + account.getRole());
    if (!account.getRole().equals("user")) {
      food.setStatus(false);
      food.setOwner("customer");
    } else {
      food.setStatus(true);
      food.setOwner("admin");
    }
    food.setAccount(account);
    food.setCreatedAt(System.currentTimeMillis());
    food.setUpdatedAt(System.currentTimeMillis());
    DTOEntity newFood = DtoUtils.convertToDto(foodService.createFood(food), new FoodDto());
    return new ResponseObject<>(newFood, true, "Create food successfully.");
  }

  @PutMapping(value = "/update")
  public ResponseObject<DTOEntity> updateFood(@RequestBody FoodDto foodDto) {
    Food oldFood = foodService.getFood(foodDto.getFoodId());
    Food food = (Food) DtoUtils.convertToEntity(new Food(), foodDto);
    food.setAccount(oldFood.getAccount());
    food.setStatus(false);
    food.setOwner("customer");
    DTOEntity newFood = DtoUtils.convertToDto(foodService.updateFood(food), new FoodDto());
    return new ResponseObject<>(newFood, true, "Create food successfully.");
  }


  @GetMapping(value = "/search")
  public ResponseObject<List<DTOEntity>> listFood(@RequestParam("foodName") String foodName, HttpServletRequest request) {
	  String rsEmail = request.getAttribute("rsEmail").toString();
	    Account account = accountRepository.findByEmail(rsEmail);
    List<DTOEntity> listFood = foodService.searchFood(foodName,account.getAccountId()).stream()
        .map(entity -> DtoUtils.convertToDto(entity, new FoodDto())).collect(Collectors.toList());
    return new ResponseObject<>(listFood, true, "Get list food successfully.");
  }

  @PutMapping(value = "/active")
  public ResponseObject<DTOEntity> activeFood(@RequestBody FoodDto foodDto) {
    Food oldFood = foodService.getFood(foodDto.getFoodId());
    Food food = (Food) DtoUtils.convertToEntity(new Food(), foodDto);
    food.setAccount(oldFood.getAccount());
    food.setStatus(true);
    DTOEntity newFood = DtoUtils.convertToDto(foodService.updateFood(food), new FoodDto());
    return new ResponseObject<>(newFood, true, "Create food successfully.");
  }
  
  @GetMapping(value = "/{foodId}")
  public ResponseObject<DTOEntity> getFood(@PathVariable Long foodId) {
    DTOEntity newFood = DtoUtils.convertToDto(foodService.getFood(foodId), new FoodDto());
    return new ResponseObject<>(newFood, true, "get food successfully.");
  }

  @GetMapping(value = "/all-foods")
  @ResponseBody
  public ResponseObject<List<DTOEntity>> foodAll(HttpServletRequest request) {
    String rsEmail = request.getAttribute("rsEmail").toString();
    Account account = accountRepository.findByEmail(rsEmail);
    if (account == null) {
      logger.info("account null,error forbidden!");
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
    }
    if (!account.getRole().equals("admin")) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Error forbidden!");
    }
    List<DTOEntity> list = foodService.allFood().stream()
            .map(entity -> DtoUtils.convertToDto(entity, new FoodDto())).collect(Collectors.toList());
    return new ResponseObject<>(list, true, "Get list food successfully.");
  }
}

package com.uok.foodcatalog.controller;

import com.uok.foodcatalog.dto.FoodCatalogPage;
import com.uok.foodcatalog.dto.FoodItemDTO;
import com.uok.foodcatalog.service.FoodItemsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@RequiredArgsConstructor
@Tag(name = "Food Catalogue Management")
@CrossOrigin
public class FoodCatalogController {

    private final FoodItemsService foodItemsService;

    @PostMapping("/addFoodItem")
    public void addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        foodItemsService.addFoodItem(foodItemDTO);
    }

    @GetMapping("/getFoodItem/{id}")
    public ResponseEntity<FoodItemDTO> getFoodItem(@PathVariable int id) {
        return foodItemsService.getFoodItem(id);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{id}")
    public ResponseEntity<FoodCatalogPage> fetchRestaurantDetailsByFoodMenu(@PathVariable Integer id) {
        System.out.println("worked");
        FoodCatalogPage foodCatalogPage = foodItemsService.fetchFoodCataloguePageDetails(id);
        return new ResponseEntity<>(foodCatalogPage, HttpStatus.OK);
    }



}

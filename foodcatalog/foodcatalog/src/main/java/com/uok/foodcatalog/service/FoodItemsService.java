package com.uok.foodcatalog.service;

import com.uok.foodcatalog.dto.FoodCatalogPage;
import com.uok.foodcatalog.dto.FoodItemDTO;
import com.uok.foodcatalog.dto.Restaurant;
import com.uok.foodcatalog.entity.FoodItem;
import com.uok.foodcatalog.repository.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodItemsService {


    private final FoodItemRepository foodItemRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    public void addFoodItem(FoodItemDTO foodItemDTO) {
        // save food item in db
        foodItemRepository.save(modelMapper.map(foodItemDTO, FoodItem.class));
    }

    public ResponseEntity<FoodItemDTO> getFoodItem(int id) {
        FoodItem foodItem = foodItemRepository.findById(id).orElse(null);
        if (foodItem != null) {
            FoodItemDTO foodItemDTO = modelMapper.map(foodItem, FoodItemDTO.class);
            return ResponseEntity.ok(foodItemDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public FoodCatalogPage fetchFoodCataloguePageDetails(Integer restaurantId) {
        List<FoodItem> foodItems = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCatalogPage(foodItems, restaurant);
    }


    private FoodCatalogPage createFoodCatalogPage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCatalogPage foodCatalogPage = new FoodCatalogPage();
        foodCatalogPage.setFoodItemsList(foodItemList);
        foodCatalogPage.setRestaurant(restaurant);
        return foodCatalogPage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANTLISTING/restaurants/getRestaurantById/" + restaurantId, Restaurant.class);

    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        //how fetch food items from db
        return foodItemRepository.findByRestaurantId(restaurantId);

    }
}

package com.uok.restaurantlisting.controller;

import com.uok.restaurantlisting.dto.RestaurantDTO;
import com.uok.restaurantlisting.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationService;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/getAllRestaurants")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PostMapping("/addRestaurant")
    public void addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        restaurantService.addRestaurant(restaurantDTO);
    }

    @GetMapping("/getRestaurantById/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }
}

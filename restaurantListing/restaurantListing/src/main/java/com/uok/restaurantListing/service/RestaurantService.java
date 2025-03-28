package com.uok.restaurantListing.service;


import com.uok.restaurantListing.dto.RestaurantDTO;
import com.uok.restaurantListing.entity.Restaurant;
import com.uok.restaurantListing.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper mapper;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> mapper.map(restaurant, RestaurantDTO.class))
                .collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = mapper.map(restaurantDTO, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return mapper.map(savedRestaurant, RestaurantDTO.class);
    }
}
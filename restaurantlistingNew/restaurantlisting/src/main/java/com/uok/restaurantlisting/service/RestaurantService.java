package com.uok.restaurantlisting.service;

import com.uok.restaurantlisting.dto.RestaurantDTO;
import com.uok.restaurantlisting.entity.RestaurantEntity;
import com.uok.restaurantlisting.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public void addRestaurant(RestaurantDTO restaurantDTO) {
        restaurantRepository.save(modelMapper.map(restaurantDTO, RestaurantEntity.class));

    }

    public List<RestaurantDTO> getAllRestaurants(){
        return restaurantRepository.findAll().stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .toList();
    }

    public RestaurantDTO getRestaurantById(Long id) {
        return modelMapper.map(restaurantRepository.findById(id).orElseThrow(() -> new RuntimeException("Restaurant not found")), RestaurantDTO.class);
    }
}

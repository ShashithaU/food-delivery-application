package com.uok.restaurantlisting.service;

import com.uok.restaurantlisting.dto.RestaurantDTO;
import com.uok.restaurantlisting.entity.RestaurantEntity;
import com.uok.restaurantlisting.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class restaurantServiceTest {

    @InjectMocks
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRestaurants() {
        // Create mock restaurants
        List<RestaurantEntity> mockRestaurants = Arrays.asList(
                new RestaurantEntity(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new RestaurantEntity(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
        when(restaurantRepository.findAll()).thenReturn(mockRestaurants);

        // Call the service method
        List<RestaurantDTO> restaurantDTOList = restaurantService.getAllRestaurants();

        // Verify the result
        assertEquals(mockRestaurants.size(), restaurantDTOList.size());
        for (int i = 0; i < mockRestaurants.size(); i++) {
            RestaurantDTO expectedDTO = modelMapper.map(mockRestaurants.get(i), RestaurantDTO.class);
            assertEquals(expectedDTO, restaurantDTOList.get(i));
        }

        // Verify that the repository method was called
        verify(restaurantRepository, times(1)).findAll();
    }


}

package com.uok.restaurantlisting.repository;

import com.uok.restaurantlisting.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity,Long> {

}

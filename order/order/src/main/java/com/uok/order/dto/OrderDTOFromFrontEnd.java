package com.uok.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class OrderDTOFromFrontEnd {

    private List<FoodItemsDTO> foodItemsDTOList;
    private Integer userId;
    private Restaurant restaurant;
}

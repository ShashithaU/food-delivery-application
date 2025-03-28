package com.uok.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemsDTO {

    private int id;
    private String itemName;
    private String itemDescription;
    private boolean isVeg;
    private double price;
    private int restaurantId;
    private int quantity;
}

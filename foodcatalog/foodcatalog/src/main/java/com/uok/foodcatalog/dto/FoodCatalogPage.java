package com.uok.foodcatalog.dto;

import com.uok.foodcatalog.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCatalogPage {
    private List<FoodItem> foodItemsList;
    private Restaurant restaurant;

}

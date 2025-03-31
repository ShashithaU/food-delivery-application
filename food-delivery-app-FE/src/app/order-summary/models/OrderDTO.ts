import { FoodItem } from "src/app/Shared/models/FoodItem";
import { Restaurant } from "src/app/Shared/models/Restaurant";

export interface OrderDTO{

    foodItemsDTOList?: FoodItem[];
    userId?: number;
    restaurant?: Restaurant;
}
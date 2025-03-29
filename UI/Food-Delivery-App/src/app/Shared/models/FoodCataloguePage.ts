import { FoodItem } from './FoodItem';
import { Restaurant } from './Restaurants';

export interface FoodCataloguePage{
    foodItems: FoodItem[];
    restaurant: Restaurant;
}
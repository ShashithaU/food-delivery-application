import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./header/header.component";
import { RestaurantListingComponent } from './restaurant-listing/components/restaurant-listing.component';
import { FoodCatalogueComponent } from './food-catalogue/components/food-catalogue.component';
import { FoodCatalogueModule } from './food-catalogue/food-catalogue.module';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, RestaurantListingComponent,FoodCatalogueModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Food-Delivery-App';
}

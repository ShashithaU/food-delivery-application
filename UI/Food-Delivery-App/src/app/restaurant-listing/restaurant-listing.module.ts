import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantListingRoutingModule } from './restaurant-listing-routing.module';
import { RestaurantListingComponent } from './components/restaurant-listing.component';

@NgModule({
  declarations: [
    // No declarations needed for standalone components
  ],
  imports: [
    CommonModule,
    RestaurantListingRoutingModule,
    RestaurantListingComponent // Import the standalone component here
  ]
})
export class RestaurantListingModule { }

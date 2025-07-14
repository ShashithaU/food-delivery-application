import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../service/order.service';
import { OrderDTO } from '../models/OrderDTO';

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent {

  orderSummary?: OrderDTO;
  obj: any;
  total?: any;
  showDialog: boolean = false;

  constructor(private route: ActivatedRoute, private orderService: OrderService, private router: Router) { }
   
  ngOnInit() {
    try {
      const data = this.route.snapshot.queryParams['data'];
      
      if (!data) {
        console.warn('No data found in query parameters');
        this.total = 0;
        return;
      }

      this.obj = JSON.parse(data);
      this.obj.userId = 1;
      this.orderSummary = this.obj;

      console.log('Order summary structure:', this.orderSummary);

      // Check if foodItemsList exists and is an array before calling reduce
      if (this.orderSummary?.foodItemsList && Array.isArray(this.orderSummary.foodItemsList)) {
        this.total = this.orderSummary.foodItemsList.reduce((accumulator, currentValue) => {
          return accumulator + (currentValue.quantity * currentValue.price);
        }, 0);
        console.log('Calculated total:', this.total);
      } else {
        console.warn('No valid food items array found');
        this.total = 0;
      }

    } catch (error) {
      console.error('Error parsing order data:', error);
      this.total = 0;
    }
  }

saveOrder() {
  if (!this.orderSummary) {
    console.error('Cannot save order: orderSummary is undefined');
    return;
  }

  // Log the original data structure to debug
  console.log('Original orderSummary:', this.orderSummary);
  console.log('foodItemsList:', this.orderSummary.foodItemsList);

  // Transform the data to match backend expectations
  const orderData = {
    userId: this.orderSummary.userId || 1, // Add fallback
    foodItemsDTOList: this.orderSummary.foodItemsList?.map(item => ({
      id: item.id,
      itemName: item.itemName,
      itemDescription: item.itemDescription,
      isVeg: item.veg !== undefined ? item.veg : false,
      price: item.price,
      restaurantId: item.restaurantId,
      quantity: item.quantity
    })) || [],
    restaurant: this.orderSummary.restaurant
  };

  console.log('Transformed order data for backend:', orderData);
  
  // Validate the data before sending
  if (!orderData.foodItemsDTOList || orderData.foodItemsDTOList.length === 0) {
    console.error('No food items to order');
    alert('No food items to order');
    return;
  }

  if (!orderData.restaurant) {
    console.error('No restaurant data');
    alert('Missing restaurant information');
    return;
  }

  this.orderService.saveOrder(orderData)
    .subscribe({
      next: (response) => {
        console.log('Order saved successfully:', response);
        this.showDialog = true;
      },
      error: (error) => {
        console.error('Failed to save data:', error);
        console.error('Error details:', {
          status: error.status,
          statusText: error.statusText,
          url: error.url,
          message: error.message,
          error: error.error
        });
        
        if (error.status === 0) {
          console.error('❌ Cannot connect to backend. Check if service is running on port 9094');
          alert('Cannot connect to server. Please check if the backend service is running.');
        } else if (error.status === 404) {
          console.error('❌ API endpoint not found');
          alert('API endpoint not found. Please check the backend configuration.');
        } else if (error.status >= 400 && error.status < 500) {
          console.error('❌ Client error:', error.message);
          alert('Invalid request. Please check the data format.');
        } else if (error.status >= 500) {
          console.error('❌ Server error:', error.message);
          alert('Server error. Please try again later.');
        } else {
          console.error('❌ Unknown error:', error);
          alert('An unknown error occurred. Please try again.');
        }
      }
    });
}

  closeDialog() {
    this.showDialog = false;
    this.router.navigate(['/']);
  }
}
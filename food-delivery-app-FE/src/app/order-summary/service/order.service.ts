import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { API_URL_Order } from 'src/app/constants/url';
// import {  K8ExternalIp } from 'src/app/constants/url'; 

@Injectable({
  providedIn: 'root'
})

export class OrderService {

private apiUrl = API_URL_Order + '/order/saveOrder';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
      // Remove Access-Control-Allow-Origin - this should be handled by the backend
    })
  };

  saveOrder(data: any): Observable<any> {
    console.log('Making request to:', this.apiUrl);
    console.log('Request payload:', JSON.stringify(data, null, 2));
    
    return this.http.post<any>(this.apiUrl, data, this.httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any) {
    console.error('HTTP Error Details:', error);
    return throwError(() => error);
  }
}
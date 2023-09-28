import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class CycleService {

  private apiUrl = 'http://localhost:8080/api/cycles';
  constructor(private _http:HttpClient){}

getCycles(): Observable<any>
{
  return this._http.get(`${this.apiUrl}`) ;
}

getBorrowedCycles()
{
  return this._http.get(`${this.apiUrl}/borrowed`);
}

borrowCycles(cycleId: number, quantity: number) {

  return this._http.post(`${this.apiUrl}/borrow/${cycleId}`, {"quantity":quantity});

}

addToCart(cycleId: number, quantity: number) {

  return this._http.post(`${this.apiUrl}/${cycleId}/add-cart?count=${quantity}`, {},  {responseType : "text"});

}

returnCycles(cycleId: number)
{
  return this._http.post(`${this.apiUrl}/return/${cycleId}`, {});
}

addCycle(newCycle: any): Observable<any> {

  return this._http.put(`${this.apiUrl}/add`, newCycle);

}

getCartData(): Observable<any>{

  return this._http.get(`${this.apiUrl}/get-cart`);
}

removeCartItem(cycleId:any,quantity:number): Observable<any>{

  return this._http.post(`${this.apiUrl}/${cycleId}/remove-cart?count=${quantity}`, {}, {responseType:"text"});
}

checkout(totalAmount:number): Observable<any>{
  return this._http.post(`${this.apiUrl}/checkout?amount=${totalAmount}`,{},{responseType:"text"});
}

getRentalRecord(): Observable<any>{
  return this._http.get(`${this.apiUrl}/all-Purchases`);
}

}

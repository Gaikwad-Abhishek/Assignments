import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface CycleData {
  id: string;
  count: number;
}

@Injectable({
  providedIn: 'root'
})
export class CycleService {

  private apiUrl = 'http://localhost:8080/api/cycles'; 

  constructor(private http: HttpClient) {}

  borrowCycle(data: CycleData): Observable<any> {
    return this.http.post(`${this.apiUrl}/${data.id}/borrow`, data, { responseType : 'text'});
  }

  returnCycle(data: CycleData): Observable<any> {
    return this.http.post(`${this.apiUrl}/${data.id}/return`, data, { responseType : 'text'});
  }

  restockCycle(data: CycleData): Observable<any> {
    return this.http.post(`${this.apiUrl}/${data.id}/restock`, data, { responseType : 'text'});
  }

  listAvailableCycles(): Observable<any> {
    return this.http.get(`${this.apiUrl}/list-data`);
  }
}

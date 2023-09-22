import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class PostService {

  private apiUrl = 'http://localhost:8080/api/forum'; 
  postId: number;

  constructor(private http: HttpClient) {
    this.postId = 0;
  }

  setPostId(postId:number){
    this.postId = postId;
  }

  getPost(postId: number): Observable<any> {

    return this.http.get(`${this.apiUrl}/post/${postId}`);
  
  }

}